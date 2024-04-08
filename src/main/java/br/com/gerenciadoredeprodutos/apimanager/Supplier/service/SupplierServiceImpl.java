package br.com.gerenciadoredeprodutos.apimanager.Supplier.service;

import br.com.gerenciadoredeprodutos.apimanager.Supplier.dto.SupplierRequest;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.dto.SupplierResponse;
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
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public SupplierResponse create(@Valid SupplierRequest supplierRequest){
        Supplier supplier = supplierRepository.save(new Supplier(
                UUID.randomUUID(),
                supplierRequest.getName(),
                supplierRequest.getEmail(),
                supplierRequest.getCnpj(),
                LocalDateTime.now()
        ));
        return new SupplierResponse(supplier.getId(), supplier.getName(), supplier.getEmail(), supplier.getCnpj(), supplier.getCreatedAt());
    }

    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll().stream()
                .map(supplier -> new SupplierResponse(
                        supplier.getId(), supplier.getName(), supplier.getCnpj(), supplier.getEmail(), supplier.getCreatedAt()
                )).collect(Collectors.toList());
    }
    public SupplierResponse findById(UUID id){
        return supplierRepository.findById(id)
                .map(supplier -> new SupplierResponse(
                        supplier.getId(), supplier.getName(), supplier.getCnpj(), supplier.getEmail(),  supplier.getCreatedAt()
                )).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id: " + id));
    }

    public SupplierResponse update(UUID id, @Valid SupplierRequest supplierRequest){
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setName(supplierRequest.getName());
                    supplier.setEmail(supplierRequest.getEmail());
                    supplier.setCnpj(supplierRequest.getCnpj());
                    supplierRepository.save(supplier);
                    return new SupplierResponse(
                            supplier.getId(), supplier.getName(), supplier.getCnpj(), supplier.getEmail(),  supplier.getCreatedAt()
                    );
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id: " + id));
    }

    public void deleteById(UUID id){
        if (supplierRepository.existsById(id)){
            supplierRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id: " + id);
        }
    }

}
