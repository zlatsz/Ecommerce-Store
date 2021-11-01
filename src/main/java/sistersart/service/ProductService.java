package sistersart.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sistersart.model.entity.IndexProduct;
import sistersart.model.entity.Product;
import sistersart.model.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel createProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductById(String id);

    ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel);

    ProductServiceModel deleteProduct(String id);

    List<ProductServiceModel> findAllByCategory(String category);


    ProductServiceModel decreaseProductQuantity(String productId, int quantity, ProductServiceModel productServiceModel);

    List<IndexProduct> indexView();

   List<Product> findAllBySearch(String formattedInput);
}
