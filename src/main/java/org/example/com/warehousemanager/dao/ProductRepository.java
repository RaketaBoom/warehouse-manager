package org.example.com.warehousemanager.dao;

import org.example.com.warehousemanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByArticle(String article);

}