package com.example.extraapi.controller.product;

import com.example.extraapi.config.ApiResponse;
import com.example.extraapi.model.product.Product;
import com.example.extraapi.model.product.ProductDto;
import com.example.extraapi.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/product")
    public ResponseEntity<ApiResponse<List<Product>>> getAll(){
        try{
            ApiResponse<List<Product>> product = service.getAll();
            return new ResponseEntity<>(
                    product,
                    HttpStatus.OK
            );
        }catch (Exception exception){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, false, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable Integer id){
        try{
            ApiResponse<Product> product = service.getById(id);
            return new ResponseEntity<>(
                    product,
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<List<Product>>> saveProduct(@Valid @RequestBody ProductDto productDto){
        try{
            ApiResponse<Product> product = service.saveProduct(productDto);
            ApiResponse<List<Product>> allProduct = service.getAll();
            HttpStatus status = product.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    allProduct,
                    status
            );
        }catch (Exception exception){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, false, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer id){
        try{
            ApiResponse<Product> product = service.updateProduct(productDto, id);
            HttpStatus status = product.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    product,
                    status
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Product>> deleteById(@PathVariable Integer id){
        try{
            ApiResponse<Product> product = service.deleteProduct(id);
            HttpStatus status = product.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    product,
                    status
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ApiResponse<>(null, true, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
