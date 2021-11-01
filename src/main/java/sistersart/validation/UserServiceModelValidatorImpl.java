package sistersart.validation;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sistersart.model.entity.User;
import sistersart.model.service.UserServiceModel;

@Component
public class UserServiceModelValidatorImpl implements UserServiceModelValidator {


    @Override
    public boolean isValid(User user) {
        return user!=null;
    }

    @Override
    public boolean isValid(UserServiceModel user) {
        return isUsernameValid(user.getUsername()) && isEmailValid(user.getEmail())
                && isPasswordValid(user.getPassword());
    }



    boolean isUsernameValid(String userName) {
        return userName != null;
    }

    boolean isPasswordValid(String password) {
        return password != null;
    }

    boolean isEmailValid(String email) {
        return email != null;
    }
}
