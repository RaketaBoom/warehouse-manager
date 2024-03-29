package org.example.com.warehousemanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column(unique = true)
    private String article;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private Integer count;

    @Column
    private LocalDateTime dateTimeLastEdit;

    @Column
    private LocalDate dateCreation;

    @Column
    private String category;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDate.now();
        dateTimeLastEdit = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateTimeLastEdit = LocalDateTime.now();
    }
}
