package br.com.gerenciadoredeprodutos.apimanager.Costumer.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "costumer_address")
public class CostumerAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id")
    private Costumer costumer;
}
