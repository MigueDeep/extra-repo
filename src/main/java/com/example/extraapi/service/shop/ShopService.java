package com.example.extraapi.service.shop;

import com.example.extraapi.config.ApiResponse;
import com.example.extraapi.model.product.Product;
import com.example.extraapi.model.product.ProductDto;
import com.example.extraapi.model.product.ProductRepository;
import com.example.extraapi.model.shop.Shop;
import com.example.extraapi.model.shop.ShopDto;
import com.example.extraapi.model.shop.ShopRepository;
import com.example.extraapi.service.product.ProductService;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    @Autowired
    ShopRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ApiResponse<List<Shop>> findByName(String name) {
        List<Shop> shop = repository.findByName(name);
        if (!shop.isEmpty()) {
            Float total = 0f;
           for (Shop s:shop){
               String code = s.getCode();
               Product productPrice = productRepository.findByCode(code);
               Shop quantity = repository.findByCode(code);
               total += productPrice.getPrice() * quantity.getQuantity();
           }
            return new ApiResponse<>(shop, false, HttpStatus.OK, "carrito encontrado con la cantidad de: " + total);

        } else {
            return new ApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "No se encontró el carrito");
        }

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ApiResponse<Shop> saveShop(ShopDto shopDto){
        Product product = productRepository.findByCode(shopDto.getCode());
        if (product != null){
                Shop saveShop = Shop.builder()
                        .name(shopDto.getName())
                        .code(shopDto.getCode())
                        .quantity(shopDto.getQuantity())
                        .build();
                Shop shop1 = repository.save(saveShop);
                return new ApiResponse<>(shop1, false, HttpStatus.OK, "Añadido al carrito!");
        }else{
            return new ApiResponse<>(null, true, HttpStatus.OK, "No se encontró el producto!");
        }
    }



}
