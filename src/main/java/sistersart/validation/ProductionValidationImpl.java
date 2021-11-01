package sistersart.validation;


import org.springframework.stereotype.Component;
import sistersart.model.entity.Product;
import sistersart.model.service.CategoryServiceModel;
import sistersart.model.service.ProductServiceModel;

import java.util.List;

@Component
public class ProductionValidationImpl implements ProductValidation {
    @Override
    public boolean isValid(Product product) {
        return product != null;
    }

    @Override
    public boolean isValid(ProductServiceModel product) {
        return product != null
                && areCategoriesValid(product.getCategories());
    }

    private boolean areCategoriesValid(List<CategoryServiceModel> categories) {
        return categories != null && !categories.isEmpty();
    }
}
