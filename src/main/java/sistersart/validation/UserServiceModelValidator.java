package sistersart.validation;


import sistersart.model.entity.Product;
import sistersart.model.entity.User;
import sistersart.model.service.UserServiceModel;

public interface UserServiceModelValidator {

    boolean isValid(User user);

    boolean isValid(UserServiceModel user);

}
