package sistersart.web.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sistersart.error.UserWrongCredentialsException;
import sistersart.model.binding.UserEditBindingModel;
import sistersart.model.binding.UserLoginBindingModel;
import sistersart.model.binding.UserRegisterBindingModel;
import sistersart.model.service.OrderServiceModel;
import sistersart.model.service.RoleServiceModel;
import sistersart.model.service.UserServiceModel;
import sistersart.model.view.OrderViewModel;
import sistersart.model.view.UserAllViewModel;
import sistersart.model.view.UserProfileViewModel;
import sistersart.security.JwtResponse;
import sistersart.security.JwtUtils;
import sistersart.service.OrderService;
import sistersart.service.UserService;
import sistersart.validation.UserEditValidator;
import sistersart.validation.UserRegisterValidator;
import sistersart.validation.UserServiceModelValidator;
import sistersart.web.annotations.PageTitle;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController extends BaseController{

    private final UserService userService;
    private final OrderService orderService;
    private final UserRegisterValidator userRegisterValidator;
    private final UserEditValidator userEditValidator;
    private final UserServiceModelValidator userServiceModelValidator;
    private final ModelMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, OrderService orderService, UserRegisterValidator userRegisterValidator, UserEditValidator userEditValidator, UserServiceModelValidator userServiceModelValidator, ModelMapper mapper, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.orderService = orderService;
        this.userRegisterValidator = userRegisterValidator;
        this.userEditValidator = userEditValidator;
        this.userServiceModelValidator = userServiceModelValidator;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

//    @GetMapping("/register")
//    @PreAuthorize("isAnonymous()")
//    @PageTitle("Register")
//    public ModelAndView register(ModelAndView modelAndView,
//                                 @ModelAttribute(name = "model") UserRegisterBindingModel model) {
//        modelAndView.addObject("model", model);
//        return view("users/register", modelAndView);
//    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
//    public ModelAndView registerConfirm(ModelAndView modelAndView,
//                                  @ModelAttribute (name = "model") UserRegisterBindingModel model,
//                                  BindingResult bindingResult) {

    public ResponseEntity<String> registration(@Valid @RequestBody UserRegisterBindingModel model, BindingResult bindingResult){
        this.userRegisterValidator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new UserWrongCredentialsException();
        }
        UserServiceModel serviceModel = mapper.map(model, UserServiceModel.class);
        userService.registerUser(serviceModel);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginBindingModel model) {
        UserServiceModel serviceModelVal = mapper.map(model, UserServiceModel.class);
        if(!this.userServiceModelValidator.isValid(serviceModelVal)) {
            throw new UserWrongCredentialsException();

        }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
           Object principal = authentication.getPrincipal();
            UserServiceModel serviceModel = mapper.map(principal, UserServiceModel.class);

            List<String> roles = serviceModel.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    serviceModel.getId(),
                    serviceModel.getUsername(),
                    serviceModel.getEmail(),
                    roles));
    }

//    @GetMapping("/login")
//    @PreAuthorize("isAnonymous()")
//    @PageTitle("Login")
//    public ModelAndView login() {
//        return view("users/login");
//    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("User Details")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView){
        UserServiceModel user = this.userService.findUserByUserName(principal.getName());
        UserProfileViewModel model = mapper.map(user, UserProfileViewModel.class);
        modelAndView.addObject("model", model);
        List<OrderViewModel> orderViewModels = orderService.findAllOrders()
                .stream()
                .filter(p->p.getCustomer().getUsername().equals(principal.getName()))
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", orderViewModels);
        return view("users/profile", modelAndView);
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Edit User")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView){
        UserServiceModel user = this.userService.findUserByUserName(principal.getName());
        UserEditBindingModel model = mapper.map(user, UserEditBindingModel.class);
        modelAndView.addObject("model", model);

        return view("users/edit-profile", modelAndView);
    }

    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@Valid @ModelAttribute(name = "model") UserEditBindingModel userEditBindingModel,
                                           BindingResult bindingResult){

        this.userEditValidator.validate(userEditBindingModel, bindingResult);

        if (bindingResult.hasErrors()) {
            return view("users/edit-profile");
        }

        UserServiceModel userServiceModel = mapper.map(userEditBindingModel, UserServiceModel.class);
        this.userService.editUserProfile(userServiceModel, userEditBindingModel.getOldPassword());

        return redirect("/users/profile");
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("All Users")
    public ModelAndView allUsers(ModelAndView modelAndView){
        List<UserAllViewModel> users = userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = mapper.map(u, UserAllViewModel.class);
                    Set<String> authorities = getAuthoritiesToString(u);
                    user.setAuthorities(authorities);
                    return user;
                })
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);
        return view("users/users-all", modelAndView);
    }

    @PostMapping("/set-user/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView setUser(@PathVariable String id) {
        userService.setUserRole(id, "user");

        return redirect("/users/all");
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView setAdmin(@PathVariable String id) {
        userService.setUserRole(id, "admin");

        return redirect("/users/all");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteMessage(@PathVariable String id, ModelAndView modelAndView) {
        UserServiceModel user = this.userService.findUserById(id);
        List<OrderServiceModel> ordersByCustomer = this.orderService.findOrdersByCustomer(user.getUsername());
        if (!ordersByCustomer.isEmpty()){
            modelAndView.addObject("notFound", true);
            return view("users/users-all", modelAndView);
        } else {
            this.userService.deleteUser(id);
            return redirect("/users/all");
        }
    }

    private Set<String> getAuthoritiesToString(UserServiceModel userServiceModel) {
        return userServiceModel.getAuthorities()
                .stream()
                .map(RoleServiceModel::getAuthority)
                .collect(Collectors.toSet());
    }


}
