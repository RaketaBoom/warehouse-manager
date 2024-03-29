package org.example.com.warehousemanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message="name cannot be blank")
    private String name;

    @NotBlank(message="article cannot be blank")
    private String article;

    @NotBlank(message="description cannot be blank")
    private String description;

    @Positive(message="Price should be greater than 0")
    private BigDecimal price;

    @Positive(message="Price should be greater than 0")
    private Integer count;

    @NotBlank(message="category cannot be blank")
    private String category;
}
