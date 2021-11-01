package sistersart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistersart.model.entity.IndexProduct;

@Repository
public interface IndexProductRepository extends JpaRepository<IndexProduct, String> {
}
