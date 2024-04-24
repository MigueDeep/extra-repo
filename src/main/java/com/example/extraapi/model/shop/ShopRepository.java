package com.example.extraapi.model.shop;

import com.example.extraapi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    List<Shop> findByName(String name);

    Shop findByCode(String code);
}
