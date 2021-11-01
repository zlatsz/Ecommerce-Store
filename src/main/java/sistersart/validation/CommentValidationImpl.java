package sistersart.validation;

import org.springframework.stereotype.Component;
import sistersart.model.entity.Comment;
import sistersart.model.service.ArticleServiceModel;
import sistersart.model.service.ArticleServiceModel;
import sistersart.model.service.CommentServiceModel;

@Component
public class CommentValidationImpl implements CommentValidation {
    @Override
    public boolean isValid(Comment comment) {
       return comment != null;
    }

    @Override
    public boolean isValid(CommentServiceModel comment) {
        return comment != null
                && areArticlesValid(comment.getArticle());
    }

    private boolean areArticlesValid(ArticleServiceModel article) {
        return article != null;
    }
}
