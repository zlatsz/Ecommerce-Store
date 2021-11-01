package sistersart.validation;

import sistersart.model.entity.Article;
import sistersart.model.entity.Category;
import sistersart.model.service.ArticleServiceModel;
import sistersart.model.service.CategoryServiceModel;

public interface CategoryValidation {
    boolean isValid(Category category);

    boolean isValid(CategoryServiceModel categoryServiceModel);
}
