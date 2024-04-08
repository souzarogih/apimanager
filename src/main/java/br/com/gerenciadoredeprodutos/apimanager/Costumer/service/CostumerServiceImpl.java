package br.com.gerenciadoredeprodutos.apimanager.Costumer.service;

import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerAddressResponse;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerRequest;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.dto.CostumerResponse;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.model.Costumer;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.model.CostumerAddress;
import br.com.gerenciadoredeprodutos.apimanager.Costumer.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CostumerServiceImpl implements CostumerService{

    @Autowired
    CostumerRepository costumerRepository;

    @Override
    public CostumerResponse create(CostumerRequest costumerRequest) {

        Costumer costumer = new Costumer(
                UUID.randomUUID(),
                costumerRequest.getName(),
                costumerRequest.getCpf(),
                costumerRequest.getEmail(),
                null,
                LocalDateTime.now(),
                null
        );

        CostumerAddress costumerAddress = new CostumerAddress(
                UUID.randomUUID(),
                costumerRequest.getCostumerAddressRequest().getStreet(),
                costumerRequest.getCostumerAddressRequest().getNumber(),
                costumerRequest.getCostumerAddressRequest().getNeighborhood(),
                costumerRequest.getCostumerAddressRequest().getCity(),
                costumerRequest.getCostumerAddressRequest().getState(),
                costumerRequest.getCostumerAddressRequest().getZipcode(),
                costumerRequest.getCostumerAddressRequest().getZipcode(),
                costumer
        );

        costumer.setAddress(costumerAddress);

        Costumer savedCostumer = costumerRepository.save(costumer);

        return new CostumerResponse(
                savedCostumer.getId(),
                savedCostumer.getName(),
                savedCostumer.getCpf(),
                savedCostumer.getEmail(),
                new CostumerAddressResponse(
                        savedCostumer.getAddress().getStreet(),
                        savedCostumer.getAddress().getNumber(),
                        savedCostumer.getAddress().getNeighborhood(),
                        savedCostumer.getAddress().getCity(),
                        savedCostumer.getAddress().getState(),
                        savedCostumer.getAddress().getCountry(),
                        savedCostumer.getAddress().getZipcode()
                ),
                savedCostumer.getCreatedAt()
        );

    }

    @Override
    public List<CostumerRequest> findAll() {
        return null;
    }

    @Override
    public CostumerRequest findById(UUID id) {
        return null;
    }

    @Override
    public CostumerRequest update(UUID id, CostumerRequest costumerRequest) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
