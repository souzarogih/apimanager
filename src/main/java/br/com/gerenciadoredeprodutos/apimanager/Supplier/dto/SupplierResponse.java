package br.com.gerenciadoredeprodutos.apimanager.Supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SupplierResponse {

    private UUID id;

    private String name;

    private String cnpj;

    private String email;

    private LocalDateTime createdAt;
}
