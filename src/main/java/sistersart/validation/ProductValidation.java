package sistersart.validation;

import sistersart.model.entity.Product;
import sistersart.model.service.ProductServiceModel;

public interface ProductValidation {
    boolean isValid(Product product);

    boolean isValid(ProductServiceModel product);
}
