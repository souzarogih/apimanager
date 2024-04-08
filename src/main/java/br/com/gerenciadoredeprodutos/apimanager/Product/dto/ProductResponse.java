package br.com.gerenciadoredeprodutos.apimanager.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private UUID id;

    private String name;

    private BigDecimal price;

    private ProductSupplierDetails productSupplierDetails;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
