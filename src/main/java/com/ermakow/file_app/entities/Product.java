package com.ermakow.file_app.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<FileInfo> fileInfos;

    @Column(name = "production_year")
    private Integer year;

    @Column(name = "cc")
    private Integer cc;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
