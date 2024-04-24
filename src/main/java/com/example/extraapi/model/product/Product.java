package com.example.extraapi.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "price", nullable = false)
    Float price;

    @Column(name = "code", nullable = false, length = 35)
    String code;

    @Column(name = "category", nullable = false, length = 50)
    String description;

}