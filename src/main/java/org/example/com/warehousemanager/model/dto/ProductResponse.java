package org.example.com.warehousemanager.model.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.com.warehousemanager.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductResponse {
    private UUID id;

    private String name;

    private String article;

    private String description;

    private BigDecimal price;

    private Integer count;

    private LocalDateTime dateTimeLastEdit;

    private LocalDate dateCreation;

    private String category;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.article = product.getArticle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.count = product.getCount();
        this.dateTimeLastEdit = product.getDateTimeLastEdit();
        this.dateCreation = product.getDateCreation();
        this.category = product.getCategory();
    }
}
