package sistersart.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import sistersart.model.binding.CategoryAddBindingModel;
import org.springframework.validation.Validator;

@Component
public class CategoryEditValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryAddBindingModel categoryAddBindingModel = (CategoryAddBindingModel) o;

        if (categoryAddBindingModel.getName() == null || categoryAddBindingModel.getName().equals("")) {
            errors.rejectValue("name",
                    "Category can't be null",
                    "Category can't be null");
        }

        if (categoryAddBindingModel.getName().length() < 3
                || categoryAddBindingModel.getName().length() > 20) {
            errors.rejectValue("name",
                    "Category must be between 3 and 20 character",
                    "Category must be between 3 and 20 character");
        }
    }
}
