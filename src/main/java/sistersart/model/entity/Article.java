package sistersart.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article extends BaseEntity{

    private String name;
    private String pdfUrl;
    private LocalDateTime postTime;
    private User uploader;
    private Set<Comment> comments;

    public Article() {
    }

    @Column(name = "article_name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "pdf")
    public String getPdfUrl() {
        return this.pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    @Column(name = "post_time")
    public LocalDateTime getPostTime() {
        return this.postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUploader() {
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    @OneToMany(mappedBy="article", fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
