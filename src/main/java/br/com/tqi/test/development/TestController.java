package br.com.tqi.test.development;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test-tqi")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/client")
    public ResponseEntity<List<ClientEntity>> getAllClient() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clientRepository.findById(id).get());
    }

    @PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> saveNewClient(@RequestBody ClientEntity clientEntity) {
        return ResponseEntity.ok(testService.saveNewClient(clientEntity));
    }

    @PostMapping(value = "/client/{id}/change-address/{idAddress}")
    public ResponseEntity<Void> changeClientAddress(
            @PathVariable("id") Long id,
            @PathVariable("idAddress") Long idAddress,
            @RequestBody AddressEntity addressEntity) throws Exception {
        testService.changeClientAddress(id, idAddress, addressEntity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/address/{cep}")
    public ResponseEntity<AddressEntity> getAddressByCEP(@PathVariable("cep") String cep) throws JsonProcessingException {
        return ResponseEntity.ok(testService.getAddressByCep(cep));
    }
}
