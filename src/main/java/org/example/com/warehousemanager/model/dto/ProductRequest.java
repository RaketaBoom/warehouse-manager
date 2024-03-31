package org.example.com.warehousemanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "name cannot be blank")
    @Schema(description = "Product name")
    private String name;

    @NotBlank(message = "article cannot be blank")
    @Schema(description = "Product article. Is unique for each product")
    private String article;

    @NotBlank(message = "description cannot be blank")
    @Schema(description = "Product description")
    private String description;

    @Min(value = 0, message = "Price should be greater than 0")
    @Schema(description = "Product price in dollars", example = "1.00")
    private BigDecimal price;

    @Min(value = 1, message = "Price should be greater than 0. Should be greater than 0")
    @Schema(description = "Count of this product", example = "1")
    private Integer count;

    @NotBlank(message = "category cannot be blank")
    @Schema(description = "Product category")
    private String category;
}
