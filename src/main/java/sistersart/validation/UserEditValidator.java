package sistersart.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import sistersart.model.binding.UserEditBindingModel;
import sistersart.model.entity.User;
import sistersart.repository.UserRepository;
import org.springframework.validation.Validator;


@Component
public class UserEditValidator implements Validator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserEditValidator(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEditBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object model, Errors errors) {
        UserEditBindingModel userEditBindingModel = (UserEditBindingModel) model;

        User user = this.userRepository.findByUsername(userEditBindingModel.getUsername())
                .orElse(null);

        if(user!=null) {
            if (!this.bCryptPasswordEncoder.matches(userEditBindingModel.getOldPassword(), user.getPassword())) {
                errors.rejectValue("oldPassword", "Wrong password!", "Wrong password!");
            }

            if (userEditBindingModel.getPassword() != null &&
                    !userEditBindingModel.getPassword().equals(userEditBindingModel.getConfirmPassword())) {
                errors.rejectValue("password", "Passwords don't match!", "Passwords don't match!");
            }

            if (!user.getEmail().equals(userEditBindingModel.getEmail()) &&
                    this.userRepository.findByEmail(userEditBindingModel.getEmail()).isPresent()) {
                errors.rejectValue(
                        "email",
                        String.format("Email %s already exists", userEditBindingModel.getEmail()),
                        String.format("Email %s already exists", userEditBindingModel.getEmail())
                );
            }
        }
    }
}
