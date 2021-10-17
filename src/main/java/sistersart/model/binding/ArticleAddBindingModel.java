package sistersart.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import sistersart.model.entity.User;
import sistersart.model.service.CommentServiceModel;
import sistersart.model.service.UserServiceModel;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleAddBindingModel {

    private String name;
    private MultipartFile pdfUrl;
    private LocalDateTime postTime;


    public ArticleAddBindingModel() {
    }

    @NotNull
    @NotEmpty
    @Length(min=3, max = 20,
            message = "Name must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @NotEmpty
    public MultipartFile getPdfUrl() {
        return pdfUrl;
    }
    public void setPdfUrl(MultipartFile pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    @NotNull
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }


}
