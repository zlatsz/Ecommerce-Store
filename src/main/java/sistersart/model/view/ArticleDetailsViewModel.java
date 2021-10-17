package sistersart.model.view;

import sistersart.model.entity.User;
import sistersart.model.service.CommentServiceModel;
import sistersart.model.service.UserServiceModel;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleDetailsViewModel {

    private String id;
    private String name;
    private String pdfUrl;
    private LocalDateTime postTime;
    private UserServiceModel uploader;

    public ArticleDetailsViewModel() {
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

}
