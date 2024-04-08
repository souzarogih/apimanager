package br.com.gerenciadoredeprodutos.apimanager.Costumer.controller;

import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerRequest;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerResponse;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.service.CostumerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costumers")
public class CostumerController {

    @Autowired
    CostumerService costumerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CostumerResponse create(@Valid @RequestBody CostumerRequest costumerRequest) {
        return costumerService.create(costumerRequest);
    }
}
