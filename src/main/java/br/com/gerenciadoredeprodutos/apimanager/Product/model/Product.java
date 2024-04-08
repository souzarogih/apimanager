package br.com.gerenciadoredeprodutos.apimanager.Product.model;

import br.com.gerenciadoredeprodutos.apimanager.Supplier.model.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
