package com.example.extraapi.model.product;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@Builder
public class ProductDto {

    @Pattern(regexp = "[a-zA-Z ]+$", message = "El nombre no es valido")
    @NotBlank(message = "El nombre es requerido")
    @Length(max = 30, message = "El nombre no puede ser mayor a 30 caracteres")
    String name;

    @DecimalMin(value = "0.00", message = "El precio no puede ser menor a 0")
    @Min(value = 0, message = "El valor no puede ser menor a 0")
    @NotNull(message = "El precio es requerido")
    Float price;


    @NotBlank(message = "El codigo es necesario")
    String code;

    @NotBlank(message = "La descripcion es necesaria")
    @Length(max = 300, message = "La descripcion no puede tener mas de 300 caracteres")
    String description;
}
