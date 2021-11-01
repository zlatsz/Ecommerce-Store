package sistersart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistersart.model.entity.Article;
import sistersart.model.entity.Comment;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    void deleteByCommentDateBefore(Instant endTime);

}
