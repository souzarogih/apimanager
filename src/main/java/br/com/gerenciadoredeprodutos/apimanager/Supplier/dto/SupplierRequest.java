package br.com.gerenciadoredeprodutos.apimanager.Supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class SupplierRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @CNPJ(message = "Invalid CNPJ")
    private String cnpj;

    @Email(message = "Invalid e-mail address")
    private String email;
}
