package sistersart.validation;

import org.springframework.stereotype.Component;
import sistersart.model.entity.Article;
import sistersart.model.service.ArticleServiceModel;

@Component
public class ArticleValidationImpl implements ArticleValidation {
    @Override
    public boolean isValid(Article article) {
        return article != null;
    }

    @Override
    public boolean isValid(ArticleServiceModel articleServiceModel) {
        return articleServiceModel != null;
    }
}
