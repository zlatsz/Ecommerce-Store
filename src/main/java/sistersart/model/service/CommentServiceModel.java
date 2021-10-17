package sistersart.model.service;

import sistersart.model.entity.Article;
import sistersart.model.entity.User;

import java.time.LocalDateTime;

public class CommentServiceModel extends BaseServiceModel {

    private String description;
    private LocalDateTime commentDate;
    private UserServiceModel user;
    private ArticleServiceModel article;

    public CommentServiceModel() {
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
