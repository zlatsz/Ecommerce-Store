package sistersart.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sistersart.error.PasswordDontMatchException;
import sistersart.model.entity.User;
import sistersart.model.service.UserServiceModel;
import sistersart.repository.UserRepository;
import sistersart.service.RoleService;
import sistersart.service.UserService;
import sistersart.error.UserNameTakenException;
import sistersart.error.UserWrongCredentialsException;
import sistersart.validation.UserServiceModelValidator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserServiceModelValidator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceModelValidator validator) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.validator = validator;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        if (!validator.isValid(userServiceModel)){
            throw new UserWrongCredentialsException("User was not created");
        }

        if (userRepository.findByUsername(userServiceModel.getUsername()).isPresent()){
            throw new UserNameTakenException("User name is already taken!");
        }

        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Incorrect user or password"));
    }

    @Override
    public UserServiceModel findUserByUserName(String username)  throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {

        User user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new PasswordDontMatchException("Incorrect password!");
        }

        user.setPassword(userServiceModel.getPassword() != null ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setEmail(userServiceModel.getEmail());

        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void setUserRole(String id, String role) throws UsernameNotFoundException {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Not such user"));

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getAuthorities().clear();

        if(role.equals("user")){
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        } else if (role.equals("admin")) {
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
        }


        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findUserById(id);
        user.getAuthorities().clear();
        this.userRepository.delete(user);
    }

    @Override
    public UserServiceModel findUserById(String id) {
        return this.modelMapper.map(this.userRepository.findUserById(id), UserServiceModel.class);
    }

}
