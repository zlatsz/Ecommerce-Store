package sistersart.validation;

import sistersart.model.entity.Comment;
import sistersart.model.service.CommentServiceModel;

public interface CommentValidation {
    boolean isValid(Comment comment);

    boolean isValid(CommentServiceModel comment);
}
