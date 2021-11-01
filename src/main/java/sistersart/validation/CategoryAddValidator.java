package sistersart.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import sistersart.model.binding.CategoryAddBindingModel;
import sistersart.repository.CategoryRepository;
import org.springframework.validation.Validator;

@Component
public class CategoryAddValidator implements Validator {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryAddValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {

        CategoryAddBindingModel categoryAddBindingModel = (CategoryAddBindingModel) model;

        if (categoryAddBindingModel.getMainCategory() == null) {
            errors.rejectValue(
                    "name", "Main Category can't be null", "Main Category can't be null");
        }

        if (categoryAddBindingModel.getName() == null || categoryAddBindingModel.getName().equals("")) {
            errors.rejectValue(
                    "name", "Category can't be null", "Category can't be null");
        }

        if (this.categoryRepository.findByName(categoryAddBindingModel.getName()).isPresent()) {
            errors.rejectValue(
                    "name",
                    String.format("Category %s already exists", categoryAddBindingModel.getName()),
                    String.format("Category %s already exists", categoryAddBindingModel.getName())
            );
        }

        if(!Character.isUpperCase(categoryAddBindingModel.getName().charAt(0))){
            errors.rejectValue(
                    "name",
                    "First character is incorrect",
                    "First character is incorrect"
            );
        }

        if (categoryAddBindingModel.getName().length() < 3 || categoryAddBindingModel.getName().length() > 20) {
            errors.rejectValue(
                    "name",
                    "Category must be between 3 and 20 character",
                    "Category must be between 3 and 20 character"
            );
        }


    }
}
