package sistersart.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sistersart.model.binding.ProductAddBindingModel;
import sistersart.repository.ProductRepository;

import java.math.BigDecimal;

@Component
public class ProductEditValidator implements Validator {

    private final ProductRepository productRepository;

    @Autowired
    public ProductEditValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object task, Errors errors) {
        ProductAddBindingModel productAddBindingModel = (ProductAddBindingModel) task;

        if (productAddBindingModel.getName() == null
                || productAddBindingModel.getName().equals("")) {
            errors.rejectValue("name",
                    "Product name can't be null",
                    "Product name can't be null");
        }

        if (productAddBindingModel.getName().length() < 3
                || productAddBindingModel.getName().length() > 40) {
            errors.rejectValue("name",
                    "Category must be between 3 and 40 character",
                    "Category must be between 3 and 40 character");
        }

        if (productAddBindingModel.getDescription() == null
                || productAddBindingModel.getDescription().equals("")) {
            errors.rejectValue("description",
                    "Description can't be null",
                    "Description can't be null");
        }

        if (productAddBindingModel.getDescription().length() < 15
                || productAddBindingModel.getDescription().length() > 100) {
            errors.rejectValue("description",
                    "Description must be between 15 and 100 character",
                    "Description must be between 15 and 100 character");
        }

        if (productAddBindingModel.getPrice() == null
                || productAddBindingModel.getPrice().toString().equals("")) {
            errors.rejectValue("price",
                    "Price can't be null",
                    "Price can't be null");
        }

        if (productAddBindingModel.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            errors.rejectValue("price",
                    "Price can't be negative",
                    "Price can't be negative");
        }

        if (productAddBindingModel.getQuantity() == null) {
            errors.rejectValue("quantity",
                    "Quantity can't be null",
                    "Quantity can't be null");
        }

        if (productAddBindingModel.getQuantity() < 0) {
            errors.rejectValue("quantity",
                    "Quantity can't be negative",
                    "Quantity can't be negative");
        }

        if (productAddBindingModel.getCategories() == null
                || productAddBindingModel.getCategories().isEmpty()) {
            errors.rejectValue("categories",
                    "Category can't be null",
                    "Category can't be null");
        }
    }
}
