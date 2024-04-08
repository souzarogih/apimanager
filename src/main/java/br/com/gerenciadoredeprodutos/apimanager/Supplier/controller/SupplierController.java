package br.com.gerenciadoredeprodutos.apimanager.Supplier.controller;

import br.com.gerenciadoredeprodutos.apimanager.Supplier.dto.SupplierRequest;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.dto.SupplierResponse;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponse create(@Valid @RequestBody SupplierRequest supplierRequest){
        return supplierService.create(supplierRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SupplierResponse> getAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponse findById(@PathVariable UUID id){
        return supplierService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SupplierResponse update(@PathVariable UUID id, @Valid @RequestBody SupplierRequest supplierRequest){
        return supplierService.update(id, supplierRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id){
        supplierService.deleteById(id);
    }

}