package br.com.gerenciadoredeprodutos.apimanager.Product.repository;

import br.com.gerenciadoredeprodutos.apimanager.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
