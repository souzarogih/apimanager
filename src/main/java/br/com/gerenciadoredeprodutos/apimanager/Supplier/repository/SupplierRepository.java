package br.com.gerenciadoredeprodutos.apimanager.Supplier.repository;

import br.com.gerenciadoredeprodutos.apimanager.Supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
