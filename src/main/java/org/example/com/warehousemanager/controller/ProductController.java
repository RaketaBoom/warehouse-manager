package org.example.com.warehousemanager.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.example.com.warehousemanager.model.Product;
import org.example.com.warehousemanager.model.dto.ProductRequest;
import org.example.com.warehousemanager.model.dto.ProductResponse;
import org.example.com.warehousemanager.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rest/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> allProducts = productService.findAllProducts().stream().map(ProductResponse::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }


    @GetMapping
    public ResponseEntity<ProductResponse> getProductByArticle(@Param("article") @Nullable String article) {
        Product product = productService.findProductByArticle(article);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        Product product = productService.saveProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") UUID id) {
        Product product = productService.findProduct(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") UUID id, @RequestBody @Validated ProductRequest productRequest) {
        Product product = productService.updateProduct(productRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
