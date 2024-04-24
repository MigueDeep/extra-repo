package com.example.extraapi.controller.shop;

import com.example.extraapi.config.ApiResponse;
import com.example.extraapi.model.product.Product;
import com.example.extraapi.model.product.ProductDto;
import com.example.extraapi.model.shop.Shop;
import com.example.extraapi.model.shop.ShopDto;
import com.example.extraapi.service.product.ProductService;
import com.example.extraapi.service.shop.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ShopController {

    @Autowired
    ShopService service;

    @PostMapping("/shop")
    public ResponseEntity<ApiResponse<Shop>> saveShop(@Valid @RequestBody ShopDto shopDto){
        try{
            ApiResponse<Shop> shop = service.saveShop(shopDto);
            HttpStatus status = shop.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    shop,
                    status
            );
        }catch (Exception exception){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, false, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @GetMapping("/shop/{name}")
    public ResponseEntity<ApiResponse<List<Shop>>> getById(@PathVariable String name){
        try{
            ApiResponse< List<Shop>> shop = service.findByName(name);
            return new ResponseEntity<>(
                    shop,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
