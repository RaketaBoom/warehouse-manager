package org.example.com.warehousemanager.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Product id")
    private UUID id;

    @Schema(description = "Product name")
    private String name;

    @Schema(description = "Product article. Is unique for each product")
    private String article;

    @Schema(description = "Product description")
    private String description;

    @Schema(description = "Product price in dollars", example = "1.00")
    private BigDecimal price;

    @Schema(description = "Count of this product", example = "1")
    private Integer count;

    @Schema(description = "Date and time of last modification")
    private LocalDateTime dateTimeLastEdit;

    @Schema(description = "Date of creation product")
    private LocalDate dateCreation;

    @Schema(description = "Product category")
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
