package sistersart.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import sistersart.model.service.ArticleServiceModel;
import sistersart.model.service.UserServiceModel;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CommentAddBindingModel {

    private String description;
    private LocalDateTime commentDate;
    private UserServiceModel user;
    private ArticleServiceModel article;

    public CommentAddBindingModel() {
    }

    @NotNull
    @NotEmpty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    @NotNull
    @NotEmpty
    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    @NotNull
    @NotEmpty
    public ArticleServiceModel getArticle() {
        return article;
    }

    public void setArticle(ArticleServiceModel article) {
        this.article = article;
    }
}
