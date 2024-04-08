package br.com.gerenciadoredeprodutos.apimanager.Product.service;

import br.com.gerenciadoredeprodutos.apimanager.Product.dto.ProductRequest;
import br.com.gerenciadoredeprodutos.apimanager.Product.dto.ProductResponse;
import br.com.gerenciadoredeprodutos.apimanager.Product.dto.ProductSupplierDetails;
import br.com.gerenciadoredeprodutos.apimanager.Product.model.Product;
import br.com.gerenciadoredeprodutos.apimanager.Product.repository.ProductRepository;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.model.Supplier;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.repository.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public ProductResponse create(@Valid ProductRequest productRequest) {

        Supplier supplier = supplierRepository
                .findById(productRequest.getSupplierId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id: " + productRequest.getSupplierId()));

        Product product = productRepository.save(new Product(
                UUID.randomUUID(), productRequest.getName(), productRequest.getPrice(), supplier, LocalDateTime.now(), null
        ));
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                new ProductSupplierDetails(supplier.getId(), supplier.getName()),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Override
    public List<ProductResponse> findAll() {
        return null;
    }

    @Override
    public ProductResponse findById(UUID id) {
        return null;
    }

    @Override
    public ProductResponse update(UUID id, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
