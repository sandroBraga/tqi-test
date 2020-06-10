package br.com.tqi.test.development.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tqi.test.development.dto.AddressDTO;
import br.com.tqi.test.development.dto.ClientDTO;
import br.com.tqi.test.development.services.TestService;

@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class TestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TestService testServiceMock;
	
	@Test
	public void deveriaRetornar200QuandoInserirClienteValido() throws Exception {
		ClientDTO clientDTO = new ClientDTO();
		AddressDTO addressDTO = new AddressDTO();
		clientDTO.setCpf("11422566866");
		clientDTO.setNome("Thrall");
		clientDTO.setSexo("Masculino");
		
		addressDTO.setBairro("recanto");
		addressDTO.setCep("38785000");
		addressDTO.setCidade("Lagamar");
		addressDTO.setComplemento("Casa");
		addressDTO.setEndereco("Rua Coromandel");
		addressDTO.setEstado("MG");
		addressDTO.setNumero("117");
		addressDTO.setPais("Brasil");
		
		clientDTO.setAddress(addressDTO);

		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(clientDTO);
		
		System.out.println(body);
		
		given(testServiceMock.saveNewClient(Mockito.any(ClientDTO.class))).willReturn(clientDTO);
        mockMvc.perform(post("/test-tqi/client").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk());
        verify(testServiceMock, VerificationModeFactory.times(1)).saveNewClient(Mockito.any(ClientDTO.class));
        reset(testServiceMock);
		
	}
	
	@Test
	public void deveriaRetornar404QuandoRotaNaoExiste() throws Exception {
		mockMvc.perform(get("/aniceurl"))
		.andExpect(status().isNotFound());
		reset(testServiceMock);
	}

}
