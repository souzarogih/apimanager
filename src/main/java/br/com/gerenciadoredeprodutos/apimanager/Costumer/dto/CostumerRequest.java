package br.com.gerenciadoredeprodutos.apimanager.Costumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostumerRequest {

    private String name;
    private String cpf;
    private String email;
    private CostumerAddressRequest costumerAddressRequest;

}
