package br.com.tqi.test.development.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.tqi.test.development.dto.AddressDTO;
import br.com.tqi.test.development.dto.ClientDTO;
import br.com.tqi.test.development.entities.AddressEntity;
import br.com.tqi.test.development.entities.ClientEntity;
import br.com.tqi.test.development.repositories.AddressRepository;
import br.com.tqi.test.development.repositories.ClientRepository;

@Service
public class TestService {

    private ClientRepository clientRepository;

    private AddressRepository addressRepository;

    private RestTemplate restTemplate;

    @Autowired
    public TestService(ClientRepository clientRepository, AddressRepository addressRepository, RestTemplate restTemplate) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.restTemplate = restTemplate;
    }

    public ClientDTO saveNewClient(ClientDTO clientDTO) {
        ClientEntity newClient = clientRepository.save(new ClientEntity(clientDTO));

        return new ClientDTO(newClient);
    }
    
    public List<ClientDTO> getAllClients() {
    	return clientRepository.findAll().stream().map(ClientDTO::new)
                .collect(Collectors.toList());
    }
    
    public ClientDTO getClientById(Long clientId) throws IllegalArgumentException {
    	try {
    		Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
    		if(clientEntity.isPresent()) {
    			return new ClientDTO(clientEntity.get());
    		}
    	} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return null;
    }

    public int changeClientAddress(Long idClient, AddressDTO addressDTO) throws Exception {
        AddressEntity actualAddress = null;
        Optional<ClientEntity> clientEntity = clientRepository.findById(idClient);
        
        if(clientEntity.isPresent() && clientEntity.get().getAddressEntity() != null) {
        	actualAddress = addressRepository.findById(clientEntity.get().getAddressEntity().getId()).get();
        }
        
        if (!actualAddress.getClient().getId().equals(idClient)) {
        	return 406;
        }
        
        addressRepository.save(new AddressEntity(addressDTO));
		return 200;

    }

    public AddressEntity getAddressByCep(String cep) throws JsonProcessingException {
        ResponseEntity<String> reAddress = restTemplate.getForEntity("https://viacep.com.br/ws/" + cep + "/json", String.class);

        ObjectNode objectNode = new ObjectMapper().readValue(reAddress.getBody(), ObjectNode.class);

        AddressEntity addressEntity = new AddressEntity();

        if (objectNode.has("cep")) {
            addressEntity.setCep(objectNode.get("cep").toString().replace("\"", ""));
        }

        if (objectNode.has("logradouro")) {
            addressEntity.setEndereco(objectNode.get("logradouro").toString().replace("\"", ""));
        }

        if (objectNode.has("bairro")) {
            addressEntity.setBairro(objectNode.get("bairro").toString().replace("\"", ""));
        }

        if (objectNode.has("localidade")) {
            addressEntity.setCidade(objectNode.get("localidade").toString().replace("\"", ""));
        }

        if (objectNode.has("uf")) {
            addressEntity.setEstado(objectNode.get("uf").toString().replace("\"", ""));
        }

        addressEntity.setPais("Brasil");

        return addressEntity;
    }
}
