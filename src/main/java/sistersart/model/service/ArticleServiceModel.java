package sistersart.model.service;

import sistersart.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ArticleServiceModel extends BaseServiceModel {

    private String name;
    private String pdfUrl;
    private LocalDateTime postTime;
    private UserServiceModel uploader;
    private Set<CommentServiceModel> comments;

    public ArticleServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
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
