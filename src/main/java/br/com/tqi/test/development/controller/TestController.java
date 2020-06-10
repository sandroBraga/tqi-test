package br.com.tqi.test.development.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.tqi.test.development.dto.AddressDTO;
import br.com.tqi.test.development.dto.ClientDTO;
import br.com.tqi.test.development.entities.AddressEntity;
import br.com.tqi.test.development.entities.ClientEntity;
import br.com.tqi.test.development.services.TestService;

@RestController
@RequestMapping("/test-tqi")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/client")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(testService.getAllClients());
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
    	try {
    		return ResponseEntity.ok(testService.getClientById(id));
    	} catch (IllegalArgumentException e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		} 
    }

    @PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> saveNewClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(testService.saveNewClient(clientDTO));
    }

    @PostMapping(value = "/client/change-address/{id}")
    public ResponseEntity<Void> changeClientAddress(
            @PathVariable("id") Long id,
            @RequestBody AddressDTO addressDTO) throws Exception {
        testService.changeClientAddress(id, addressDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/address/{cep}")
    public ResponseEntity<AddressEntity> getAddressByCEP(@PathVariable("cep") String cep) throws JsonProcessingException {
        return ResponseEntity.ok(testService.getAddressByCep(cep));
    }
}
