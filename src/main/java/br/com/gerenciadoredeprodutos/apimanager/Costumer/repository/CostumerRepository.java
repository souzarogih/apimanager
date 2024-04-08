package br.com.gerenciadoredeprodutos.apimanager.Costumer.repository;

import br.com.gerenciadoredeprodutos.apimanager.Costumer.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, UUID> {
}
