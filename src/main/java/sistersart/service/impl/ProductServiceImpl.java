package sistersart.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sistersart.error.ProductAlreadyExistsException;
import sistersart.error.ProductNotFoundException;
import sistersart.model.entity.Category;
import sistersart.model.entity.IndexProduct;
import sistersart.model.entity.Product;
import sistersart.model.service.ProductServiceModel;
import sistersart.repository.IndexProductRepository;
import sistersart.repository.ProductRepository;
import sistersart.service.CategoryService;
import sistersart.service.ProductService;
import sistersart.validation.ProductValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final IndexProductRepository indexProductRepository;
    private final ProductValidation productValidation;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryService categoryService, IndexProductRepository indexProductRepository, ProductValidation productValidation,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.indexProductRepository = indexProductRepository;
        this.productValidation = productValidation;
        this.modelMapper = modelMapper;

    }

    @Override
    public ProductServiceModel createProduct(ProductServiceModel productServiceModel) {
        if (!productValidation.isValid(productServiceModel)) {
            throw new ProductNotFoundException("Product not found.");
        }
        Product product = this.productRepository
                .findByName(productServiceModel.getName())
                .orElse(null);

        if (product != null) {
            throw new ProductAlreadyExistsException("Product with this name already exists");
        }
        product = this.modelMapper.map(productServiceModel, Product.class);

        List<Category> categories = productServiceModel.getCategories().stream()
                .map(c -> this.modelMapper.map(c, Category.class))
                .collect(Collectors.toList());
        product.setCategories(categories);

        return this.modelMapper.map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductById(String id) {
        return this.productRepository.findById(id)
                .map(p -> this.modelMapper
                        .map(p, ProductServiceModel.class))
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.setName(productServiceModel.getName());
        product.setDescription(productServiceModel.getDescription());
        product.setPrice(productServiceModel.getPrice());
        product.setQuantity(productServiceModel.getQuantity());
        product.setCategories(
                productServiceModel.getCategories()
                        .stream()
                        .map(c -> this.modelMapper.map(c, Category.class))
                        .collect(Collectors.toList())
        );

        return this.modelMapper
                .map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);
    }


    @Override
    public ProductServiceModel deleteProduct(String id) {
        Product product = this.productRepository
                .findById(id)
                .orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String category) {
//        List<ProductServiceModel> list = new ArrayList<>();
//        for (Product product : this.productRepository.findAll()) {
//            List<Category> categories = product.getCategories();
//            for (Category c : categories) {
//                List<CategoryName> categoryNames = c.getCategoryNames();
//                for (CategoryName categoryName : categoryNames) {
//                    if(categoryName.getName().equals(category)){
//                        ProductServiceModel map = this.modelMapper.map(product, ProductServiceModel.class);
//                        list.add(map);
//                    }
//                }
//            }
//        }
//        return list;
        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories().stream().anyMatch(categoryStream -> categoryStream.getName().equals(category)))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public ProductServiceModel decreaseProductQuantity(String productId, int value, ProductServiceModel productServiceModel) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setQuantity(product.getQuantity() - value);
        return this.modelMapper
                .map(this.productRepository.saveAndFlush(product), ProductServiceModel.class);
    }

    @Override
    public List<IndexProduct> indexView() {
        return this.indexProductRepository.findAll();
    }

//    @Scheduled(fixedRate = 50000)
    @Scheduled(fixedRate = 3600000)
    public void scheduleProducts() {
        this.indexProductRepository.deleteAll();
        List<ProductServiceModel> products = this.findAllProducts();
        if (products.size()<4) {
            return;
        }
        Random rnd = new Random();
        List<IndexProduct> offers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            IndexProduct product = this.modelMapper.map(products.get(rnd.nextInt(products.size())), IndexProduct.class);
            if(offers.stream().anyMatch(o->o.getId().equals(product.getId()))) {
                i--;
            } else {
                offers.add(product);
            }

        }
        this.indexProductRepository.saveAll(offers);
    }

    @Override
    public List<Product> findAllBySearch(String productName) {
        return this.productRepository.search(productName);
    }

}
