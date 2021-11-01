package sistersart.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sistersart.model.binding.ArticleAddBindingModel;
import sistersart.repository.ArticleRepository;

@Component
public class ArticleAddValidator implements Validator {

    private final ArticleRepository articleRepository;

    public ArticleAddValidator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ArticleAddBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object task, Errors errors) {
        ArticleAddBindingModel articleAddBindingModel = (ArticleAddBindingModel) task;

        if (articleAddBindingModel.getName() == null
                || articleAddBindingModel.getName().equals("")) {
            errors.rejectValue("name",
                    "Article name can't be null",
                    "Article name can't be null");
        }

        if (articleAddBindingModel.getName().length() < 3
                || articleAddBindingModel.getName().length() > 20) {
            errors.rejectValue("name",
                    "Article name must be between 3 and 20 character",
                    "Article name must be between 3 and 20 character");
        }

        if (articleAddBindingModel.getPdfUrl() == null
                || articleAddBindingModel.getPdfUrl().isEmpty()) {
            errors.rejectValue("pdf",
                    "Image can't be null",
                    "Image can't be null");
        }

    }
}
