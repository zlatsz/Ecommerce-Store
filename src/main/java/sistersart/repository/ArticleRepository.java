package sistersart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistersart.model.entity.Article;
import sistersart.model.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    Optional<Article> findByName(String name);

    List<Article> findAllByUploader_UsernameOrderByPostTime(String username);
}
