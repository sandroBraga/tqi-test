package br.com.tqi.test.development;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public ClientEntity saveNewClient(ClientEntity clientEntity) {
        AddressEntity addressEntity = clientEntity.getAddressEntity();

        ClientEntity newClient = clientRepository.save(new ClientEntity(clientEntity));

        if (addressEntity.getId() == null) {
            addressEntity.setClient(newClient);
            addressRepository.save(addressEntity);
        }

        newClient.setAddressEntity(addressEntity);

        return newClient;
    }

    public void changeClientAddress(Long idClient, Long idAddress, AddressEntity addressEntity) throws Exception {
        ClientEntity clientEntity = clientRepository.findById(idClient).get();

        AddressEntity actualAddress = addressRepository.findById(idAddress).get();

        if (actualAddress.getClient().equals(idClient)) {
            throw new Exception("Endereço de outro cliente");
        }

        addressEntity.setId(actualAddress.getId());

        addressRepository.save(addressEntity);
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
