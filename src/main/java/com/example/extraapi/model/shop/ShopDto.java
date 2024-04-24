package com.example.extraapi.model.shop;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ShopDto {

    @NotBlank(message = "El nombre de carrito es necesario ")
    String name;

    @NotBlank(message = "El codigo es necesario ")
    String code;

    @Min(value = 0, message = "La cantidad no puede ser menor a 0")
    @NotNull(message = "La cantidad es requerida")
    Integer quantity;

}
