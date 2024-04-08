package br.com.gerenciadoredeprodutos.apimanager.Product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

//    @NotBlank(message = "The field name is mandatory.")
    private String name;

//    @NotBlank(message = "The field price is mandatory.")
    private BigDecimal price;

//    @NotBlank(message = "The field supplierId is mandatory.")
    private UUID supplierId;
}
