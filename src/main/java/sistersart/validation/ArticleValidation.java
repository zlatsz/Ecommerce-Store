package sistersart.validation;

import sistersart.model.entity.Article;
import sistersart.model.service.ArticleServiceModel;

public interface ArticleValidation {

    boolean isValid(Article article);

    boolean isValid(ArticleServiceModel articleServiceModel);
}
