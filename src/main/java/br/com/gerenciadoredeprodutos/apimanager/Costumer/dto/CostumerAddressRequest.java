package br.com.gerenciadoredeprodutos.apimanager.Costumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CostumerAddressRequest {

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
