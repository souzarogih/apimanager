package br.com.gerenciadoredeprodutos.apimanager.Costumer.service;

import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerRequest;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface CostumerService {

    CostumerResponse create(CostumerRequest costumerRequest);

    List<CostumerRequest> findAll();

    CostumerRequest findById(UUID id);

    CostumerRequest update(UUID id, @Valid CostumerRequest costumerRequest);

    void deleteById(UUID id);
}
