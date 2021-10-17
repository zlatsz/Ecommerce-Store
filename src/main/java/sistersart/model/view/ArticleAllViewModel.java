package sistersart.model.view;

import sistersart.model.entity.User;
import sistersart.model.service.CommentServiceModel;
import sistersart.model.service.UserServiceModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ArticleAllViewModel {

    private String id;
    private String name;
    private LocalDateTime postTime;
    private UserServiceModel uploader;
    private Set<CommentServiceModel> comments;

    public ArticleAllViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public UserServiceModel getUploader() {
        return uploader;
    }

    public void setUploader(UserServiceModel uploader) {
        this.uploader = uploader;
    }

    public Set<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentServiceModel> comments) {
        this.comments = comments;
    }
}
