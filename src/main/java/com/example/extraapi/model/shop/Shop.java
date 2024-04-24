package com.example.extraapi.model.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shop")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    @Column(name = "name", nullable = false, length = 35)
    String name;

    @Column(name = "code", nullable = false, length = 35)
    String code;

    @Column(name = "quantity", nullable = false)
    Integer quantity;



}
