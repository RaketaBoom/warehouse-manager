package org.example.com.warehousemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.com.warehousemanager.dao.ProductRepository;
import org.example.com.warehousemanager.model.Product;
import org.example.com.warehousemanager.model.dto.ProductRequest;
import org.example.com.warehousemanager.model.exception.NonUniqueException;
import org.example.com.warehousemanager.model.exception.NotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }


    public Product saveProduct(ProductRequest productRequest) {
        Optional<Product> existing = productRepository.findByArticle(productRequest.getArticle());
        if (existing.isPresent())
            throw new NonUniqueException(Product.class, "Article", productRequest.getArticle());
        Product product = Product.builder()
                .name(productRequest.getName())
                .article(productRequest.getArticle())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .count(productRequest.getCount())
                .category(productRequest.getCategory())
                .build();
        return productRepository.save(product);
    }

    public Product findProduct(UUID id) {
     return productRepository.findById(id).
             orElseThrow(() -> new NotFoundException(Product.class, "id", String.valueOf(id)));
    }

    public Product updateProduct(ProductRequest productRequest, UUID id) {
        // Maybe should write exception with article
        Product product = findProduct(id);
        product.setName(productRequest.getName());
        product.setArticle(productRequest.getArticle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCount(productRequest.getCount());
        product.setCategory(productRequest.getCategory());
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        Product product = findProduct(id);
        productRepository.delete(product);
    }


}
