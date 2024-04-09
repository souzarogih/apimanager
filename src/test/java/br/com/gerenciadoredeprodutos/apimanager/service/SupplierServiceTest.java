package br.com.gerenciadoredeprodutos.apimanager.service;

import br.com.gerenciadoredeprodutos.apimanager.Supplier.dto.SupplierRequest;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.dto.SupplierResponse;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.model.Supplier;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.repository.SupplierRepository;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.service.SupplierService;
import br.com.gerenciadoredeprodutos.apimanager.Supplier.service.SupplierServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    @Captor
    private ArgumentCaptor<Supplier> supplierArgumentCaptor;

    final String ID_SUPPLIER_STRING = "3be0b9c7-d8f3-4039-8dde-1235d9d6557c";
    UUID ID_SUPPLIER = UUID.fromString(ID_SUPPLIER_STRING);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll() {

        Supplier supplier = Supplier.builder()
                .id(ID_SUPPLIER)
                .cnpj("99.177.834/0001-34")
                .email("higor.andrade@mail.com")
                .name("Higor")
                .build();

        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier));

        List<SupplierResponse> list = supplierService.findAll();

        assertEquals(1, list.size());
        Assertions.assertFalse(list.isEmpty());
    }


    @Test
    void findById() {
        Supplier supplier = Supplier.builder()
                .id(ID_SUPPLIER)
                .name("Guaraves")
                .build();

        when(supplierRepository.findById(ID_SUPPLIER))
                .thenReturn(Optional.of(supplier));

        SupplierResponse supplierResponse = supplierService.findById(ID_SUPPLIER);

        assertEquals("Guaraves", supplierResponse.getName());
    }


    @Test
    void create() {

        SupplierRequest supplierRequestToCreate = new SupplierRequest();
        supplierRequestToCreate.setName("Guaraves");
        supplierRequestToCreate.setEmail("sac@guaraves.com");

        // Criando um objeto Supplier a ser retornado após a criação
        SupplierResponse expectedSupplierResponse = new SupplierResponse();
        expectedSupplierResponse.setId(ID_SUPPLIER);
        expectedSupplierResponse.setName(supplierRequestToCreate.getName());
        expectedSupplierResponse.setEmail(supplierRequestToCreate.getEmail());

        // Configurando o comportamento do mock do repositório para retornar o objeto salvo
        when(supplierRepository.save(ArgumentMatchers.any())).thenAnswer(invocation -> {
            Supplier supplier = invocation.getArgument(0);
            supplier.setId(ID_SUPPLIER);
            supplier.setEmail("sac@guaraves.com");
            return supplier;
        });

        // Chamando o método create do serviço
        SupplierResponse createdSupplierResponse = supplierService.create(supplierRequestToCreate);

        // Verificando se o método save do repositório foi chamado com os parâmetros corretos
        verify(supplierRepository).save(ArgumentMatchers.any());

        // Verificando se o objeto retornado pelo serviço é o mesmo que foi passado como parâmetro
        assertNotNull(createdSupplierResponse);
        assertEquals(expectedSupplierResponse.getId(), createdSupplierResponse.getId());
        assertEquals(expectedSupplierResponse.getName(), createdSupplierResponse.getName());
//        assertEquals(expectedSupplierResponse.getEmail(), createdSupplierResponse.getEmail());

    }

    @Test
    void testUpdateExistingSupplier() {
        // Dados de entrada
        UUID id = UUID.randomUUID();
        SupplierRequest supplierRequest = new SupplierRequest();
        supplierRequest.setName("Novo Fornecedor");
        supplierRequest.setCnpj("12345678901234");
        supplierRequest.setEmail("novo@teste.com");

        // Fornecedor existente no banco de dados
        Supplier existingSupplier = new Supplier();
        existingSupplier.setId(id);
        existingSupplier.setName("Fornecedor Existente");
        existingSupplier.setCnpj("98765432109876");
        existingSupplier.setEmail("existente@teste.com");
        existingSupplier.setCreatedAt(LocalDateTime.now());

        // Configurando comportamento do mock do repositório
        when(supplierRepository.findById(id)).thenReturn(Optional.of(existingSupplier));

        // Chamada do método update
        supplierService.update(id, supplierRequest);

        // Verifica se o método findById do repositório foi chamado com o ID correto
        verify(supplierRepository).findById(id);

        // Verifica se o método save do repositório foi chamado com o fornecedor correto
        verify(supplierRepository).save(supplierArgumentCaptor.capture());
        Supplier capturedSupplier = supplierArgumentCaptor.getValue();
        assertEquals(supplierRequest.getName(), capturedSupplier.getName());
        assertEquals(supplierRequest.getCnpj(), capturedSupplier.getCnpj());
        assertEquals(supplierRequest.getEmail(), capturedSupplier.getEmail());
    }

    @Test
    void testUpdateNonExistingSupplier() {
        // Dados de entrada
        UUID id = UUID.randomUUID();
        SupplierRequest supplierRequest = new SupplierRequest();
        supplierRequest.setName("Novo Fornecedor");
        supplierRequest.setCnpj("12345678901234");
        supplierRequest.setEmail("novo@teste.com");

        // Configurando comportamento do mock do repositório
        when(supplierRepository.findById(id)).thenReturn(Optional.empty());

        // Chamada do método update
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            supplierService.update(id, supplierRequest);
        });

        // Verifica se o método findById do repositório foi chamado com o ID correto
        verify(supplierRepository).findById(id);

        // Verifica se uma exceção foi lançada com o status correto
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Supplier not found with id: " + id, exception.getReason());
    }


//    @Test
    void testDeleteExistingSupplier() {
        // Dados de entrada
        UUID id = ID_SUPPLIER;

        Supplier existingSupplier = new Supplier();
        existingSupplier.setId(id);
        existingSupplier.setName("Fornecedor Existente");
        existingSupplier.setCnpj("98765432109876");
        existingSupplier.setEmail("existente@teste.com");
        existingSupplier.setCreatedAt(LocalDateTime.now());

        // Configurando comportamento do mock do repositório
        when(supplierRepository.findById(id)).thenReturn(Optional.of(new Supplier()));

        // Chamada do método deleteById
        supplierService.deleteById(id);

        // Verifica se o método findById do repositório foi chamado com o ID correto
        verify(supplierRepository).findById(id);

        // Verifica se o método deleteById do repositório foi chamado
        verify(supplierRepository).deleteById(id);
    }

//    @Test
    void testDeleteNonExistingSupplier() {
        // Dados de entrada
        UUID id = UUID.randomUUID();

        // Configurando comportamento do mock do repositório
        when(supplierRepository.findById(id)).thenReturn(Optional.empty());

        // Chamada do método deleteById
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            supplierService.deleteById(id);
        });

        // Verifica se o método findById do repositório foi chamado com o ID correto
        verify(supplierRepository).findById(id);

        // Verifica se uma exceção foi lançada com o status correto
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Supplier not found with id: " + id, exception.getReason());
    }
}
