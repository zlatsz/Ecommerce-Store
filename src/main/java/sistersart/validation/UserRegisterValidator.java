package sistersart.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sistersart.model.binding.UserRegisterBindingModel;
import sistersart.repository.UserRepository;

@Component
public class UserRegisterValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) o;

        if (userRegisterBindingModel.getUsername() == null
                || userRegisterBindingModel.getUsername().equals("")) {
            errors.rejectValue("username",
                    "Type valid username",
                    "Type valid username");
        } else  if (userRegisterBindingModel.getUsername().length() < 3
                || userRegisterBindingModel.getUsername().length() > 15) {
            errors.rejectValue("username",
                    "Username must be between 3 and 15 character",
                    "Username must be between 3 and 15 character");
        }

        this.userRepository.findByUsername(userRegisterBindingModel.getUsername())
                .ifPresent((c) -> errors
                        .rejectValue("username",
                                "Duplicate username",
                                "Duplicate username"));

        this.userRepository.findByEmail(userRegisterBindingModel.getEmail())
                .ifPresent((c) -> errors
                        .rejectValue("email",
                                "Duplicate email",
                                "Duplicate email"));

        if (userRegisterBindingModel.getPassword() == null
                || userRegisterBindingModel.getPassword().equals("")) {
            errors.rejectValue("password",
                    "Type correct password",
                    "Type correct password");
        } else if (userRegisterBindingModel.getPassword().length() < 4
                || userRegisterBindingModel.getPassword().length() > 20) {
            errors.rejectValue("password",
                    "Password must be between 4 and 20 character",
                    "Password must be between 4 and 20 character");
        }

        if (!userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            errors.rejectValue("confirmPassword",
                    "Password must match",
                    "Password must match");
        }

        if (userRegisterBindingModel.getEmail() == null
                || userRegisterBindingModel.getEmail().equals("")) {
            errors.rejectValue("email",
                    "Type valid email",
                    "Type valid email");
        }


    }
}
