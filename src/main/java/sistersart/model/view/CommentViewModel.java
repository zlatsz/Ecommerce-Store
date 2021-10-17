package sistersart.model.view;

import sistersart.model.service.ArticleServiceModel;
import sistersart.model.service.UserServiceModel;

import java.time.LocalDateTime;

public class CommentViewModel {

    private String id;
    private String description;
    private LocalDateTime commentDate;
    private UserServiceModel user;
    private ArticleServiceModel article;

    public CommentViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public ArticleServiceModel getArticle() {
        return article;
    }

    public void setArticle(ArticleServiceModel article) {
        this.article = article;
    }
}
