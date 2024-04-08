package br.com.gerenciadoredeprodutos.apimanager.Costumer.dto;

import br.com.gerenciadoredeprodutos.apimanager.Costumer.model.CostumerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumerResponse {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private CostumerAddressResponse costumerAddressResponse;
    private LocalDateTime createdAt;

}
