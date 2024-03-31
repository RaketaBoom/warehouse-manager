package org.example.com.warehousemanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.com.warehousemanager.model.Product;
import org.example.com.warehousemanager.model.dto.ProductRequest;
import org.example.com.warehousemanager.model.dto.ProductResponse;
import org.example.com.warehousemanager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rest/v1/product")
@Tag(name = "Product CRUD")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products",
            description = "Get all products. Standard CRUD Read-all operation")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> allProducts = productService.findAllProducts().stream().map(ProductResponse::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }


    @Operation(summary = "Create product",
            description = "Create product. Standard CRUD Create operation")
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        Product product = productService.saveProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product));
    }


    @Operation(summary = "Get product",
            description = "Get product by id. Standard CRUD Read operation")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") UUID id) {
        Product product = productService.findProduct(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product));
    }

    @Operation(summary = "Update product",
            description = "Update product by id. Standard CRUD Update operation")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") UUID id, @RequestBody @Validated ProductRequest productRequest) {
        Product product = productService.updateProduct(productRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse(product));
    }

    @Operation(summary = "Delete product",
            description = "Delete product by id. Standard CRUD Delete operation")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
