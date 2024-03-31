package org.example.com.warehousemanager.service;

import org.example.com.warehousemanager.dao.ProductRepository;
import org.example.com.warehousemanager.model.Product;
import org.example.com.warehousemanager.model.dto.ProductRequest;
import org.example.com.warehousemanager.model.exception.NonUniqueException;
import org.example.com.warehousemanager.model.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private static final UUID UUID_PRODUCT = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");
    private static final UUID UUID_PRODUCT1 = UUID.fromString("7a8e64cc-c657-4b76-8d39-c09b7f702172");
    private static final UUID UUID_PRODUCT2 = UUID.fromString("bae0264a-1411-4fb7-acd0-f5d3a53d03e7");
    private static final UUID UUID_PRODUCT3 = UUID.fromString("a1ca7996-1adf-4c94-a774-2c00ce92d024");
    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllProducts_success() {
        when(productRepository.findAll()).thenReturn(productList());

        List<Product> allProduct = productService.findAllProducts();

        assertEquals(3, allProduct.size());
        assertTrue(allProduct.stream().anyMatch(product -> product.getId().equals(UUID_PRODUCT1)));
        assertTrue(allProduct.stream().anyMatch(product -> product.getId().equals(UUID_PRODUCT2)));
        assertTrue(allProduct.stream().anyMatch(product -> product.getId().equals(UUID_PRODUCT3)));


    }

    @Test
    void findProduct_success() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product()));

        Product product = productService.findProduct(UUID_PRODUCT);

        assertNotNull(product);
    }

    @Test
    void findProduct_NotFoundException() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrowsExactly(NotFoundException.class, () -> productService.findProduct(UUID_PRODUCT));
    }

    @Test
    void addProduct_success() {
        when(productRepository.findByArticle(anyString())).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(product());

        Product product = productService.saveProduct(productRequest());

        assertNotNull(product);
    }

    @Test
    void addProduct_NonUniqueException() {
        when(productRepository.findByArticle(anyString())).thenReturn(Optional.of(new Product()));
        when(productRepository.save(any())).thenReturn(product());

        assertThrowsExactly(NonUniqueException.class, () -> productService.saveProduct(productRequest()));
    }

    @Test
    void updateProduct_success() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product()));
        when(productRepository.save(any())).thenReturn(product());

        Product product = productService.updateProduct(productRequest(), UUID_PRODUCT);

        assertNotNull(product);
    }

    @Test
    void updateProduct_NotFoundException() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(product());

        assertThrowsExactly(NotFoundException.class, () -> productService.updateProduct(productRequest(), UUID_PRODUCT));
    }


    @Test
    void deleteProduct_success() {
        when(productRepository.findById(any())).thenReturn(Optional.of(product()));

        assertDoesNotThrow(() -> productService.deleteProduct(UUID_PRODUCT));
    }

    @Test
    void deleteProduct_NotFoundException() {
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrowsExactly(NotFoundException.class, () -> productService.deleteProduct(UUID_PRODUCT));
    }


    Product product() {
        return new Product(UUID_PRODUCT, "abc1", "678NUR", "Something about the product",
                new BigDecimal("20.33"), 15, LocalDateTime.of(2024, 4, 1, 0, 0),
                LocalDate.of(2024, 1, 1), "sex toys");

    }

    List<Product> productList() {
        List<Product> res = new ArrayList<>();
        res.add(new Product(UUID_PRODUCT1, "abc1", "111", "description1", new BigDecimal("10.15"),
                10, LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDate.of(2024, 1, 1), "category1"));
        res.add(new Product(UUID_PRODUCT2, "abc2", "222", "description2", new BigDecimal("7"),
                20, LocalDateTime.of(2024, 2, 1, 0, 0),
                LocalDate.of(2024, 2, 1), "category2"));
        res.add(new Product(UUID_PRODUCT3, "abc3", "333", "description3", new BigDecimal("90.34"),
                30, LocalDateTime.of(2024, 3, 1, 0, 0),
                LocalDate.of(2024, 3, 1), "category3"));
        return res;
    }

    ProductRequest productRequest() {
        ProductRequest res = new ProductRequest();
        res.setName("Computer mouse");
        res.setArticle("T324");
        res.setDescription("With RGB backlight");
        res.setPrice(new BigDecimal("15"));
        res.setCount(20);
        res.setCategory("Computer peripherals");
        return res;
    }
}
