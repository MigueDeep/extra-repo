package com.example.extraapi.service.product;

import com.example.extraapi.config.ApiResponse;
import com.example.extraapi.model.product.Product;
import com.example.extraapi.model.product.ProductDto;
import com.example.extraapi.model.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Transactional(readOnly = true)
    public ApiResponse<List<Product>> getAll(){
        List<Product> products = repository.findAll();
        if (products.isEmpty()){
            return new ApiResponse<>(products, true, HttpStatus.BAD_REQUEST, "No se encontraron productos registrados");
        }else{
            return new ApiResponse<>(products, false, HttpStatus.OK, "Productos encontrados");
        }
    }

    @Transactional(readOnly = true)
    public ApiResponse<Product> getById(Integer id) {
        Product product = repository.findById(id).orElse(null);
        if (product != null) {
            return new ApiResponse<>(product, false, HttpStatus.OK, "Producto encontrado");
        } else {
            return new ApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "No se encontró el producto");
        }

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ApiResponse<Product> saveProduct(ProductDto productDto){
        Product product = repository.findByName(productDto.getName());
        if (product != null){
            return new ApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "Nombre de produto ya registrado");
        }else{
            Product saveProduct = Product.builder()
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .code(productDto.getCode())
                    .description(productDto.getDescription())
                    .build();
            Product product1 = repository.save(saveProduct);
            return new ApiResponse<>(product1, false, HttpStatus.OK, "Producto registrado");
        }
    }

    @Transactional
    public ApiResponse<Product> updateProduct(ProductDto productDto, Integer id){
        Product productId = repository.findById(id).orElse(null);
        Product productName = repository.findByName(productDto.getName());
        if (productId != null){
            if (productName != null){
                return new ApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "El producto ya cuenta con un registro");
            }else{
                BeanUtils.copyProperties(productDto, productId);
                Product productUpdate = repository.save(productId);
                return new ApiResponse<>(productUpdate, false, HttpStatus.OK, "Producto actualizado");
            }
        }else{
            return new ApiResponse<>(null, true, HttpStatus.BAD_REQUEST, "Producto no encontrado");
        }
    }

    @Transactional
    public ApiResponse<Product> deleteProduct(Integer id){
        Product productId = repository.findById(id).orElse(null);
        if (productId != null){
            repository.deleteById(id);
            return new ApiResponse<>(null, true, HttpStatus.OK, "Producto eliminado correctamente");
        }else{
            return new ApiResponse<>(null, false, HttpStatus.BAD_REQUEST, "Producto no encontrado");
        }
    }


}
