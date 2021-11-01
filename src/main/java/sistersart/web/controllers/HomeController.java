package sistersart.web.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sistersart.model.binding.SearchBindingModel;
import sistersart.model.entity.IndexProduct;
import sistersart.model.entity.Product;
import sistersart.model.view.CategoryAllViewModel;
import sistersart.model.view.ProductAllViewModel;
import sistersart.service.CategoryService;
import sistersart.service.ProductService;
import sistersart.web.annotations.PageTitle;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class HomeController extends BaseController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(CategoryService categoryService, ProductService productService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Index")
    public ModelAndView index(ModelAndView modelAndView) {
        List<CategoryAllViewModel> categories = this.categoryService
                .findAllCategories()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryAllViewModel.class))
                .collect(Collectors.toList());
//        List<ProductAllViewModel> products = this.productService
//                .findAllProducts()
//                .stream()
//                .map(p -> this.modelMapper.map(p, ProductAllViewModel.class))
//                .limit(4)
//                .collect(Collectors.toList());
        List<IndexProduct> products = this.productService.indexView();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        return view("index", modelAndView);
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public ModelAndView home(ModelAndView modelAndView) {
        List<CategoryAllViewModel> categories = this.categoryService
                .findAllCategories()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryAllViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("categories", categories);
        List<ProductAllViewModel> products = this.productService
                .findAllProducts()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("products", products);
        SearchBindingModel model = new SearchBindingModel();
        modelAndView.addObject("model", model);
        return view("home", modelAndView);
    }

    @GetMapping("/aboutSisters")
    @PreAuthorize("isAnonymous()")
    @PageTitle("About Us")
    public String viewAboutUs(){
        return "about-us";
    }

    @GetMapping("/aboutOil")
    @PreAuthorize("isAnonymous()")
    @PageTitle("About Oil")
    public String viewAboutOil(){
        return "about-oil";
    }

    @GetMapping("/contacts")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Contacts")
    public String contacts(){
        return "contacts";
    }

}
