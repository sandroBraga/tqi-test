package br.com.tqi.test.development.dto;

import java.io.Serializable;

import br.com.tqi.test.development.entities.ClientEntity;

public class ClientDTO implements Serializable {
	
	private static final long serialVersionUID = 1900851632542541134L;
	
	private Long id;
	private String nome;
	private String cpf;
	private String sexo;
	private AddressDTO address;
	
	public ClientDTO() { };
	
	public ClientDTO(ClientEntity clientEntity) {
		this.id = clientEntity.getId();
		this.nome = clientEntity.getNome();
		this.sexo = clientEntity.getSexo();
		this.address = new AddressDTO(clientEntity.getAddressEntity());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	
	
}
