package br.com.gerenciadoredeprodutos.apimanager.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ProductSupplierDetails {

    private UUID id;
    private String name;

}
