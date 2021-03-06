package br.com.tqi.test.development.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.tqi.test.development.dto.AddressDTO;

@Entity
@Table(name = "ADDRESS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Endereço não pode ser vazio")
    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @NotNull
    @Column(name = "cidade")
    private String cidade;

    @NotNull(message = "Estado não pode ser vazio")
    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @NotNull
    @Column(name = "cep")
    private String cep;

    @OneToOne(mappedBy = "addressEntity")
    private ClientEntity client;

    public AddressEntity() {
        super();
    }
    
    public AddressEntity(AddressDTO address) {
    	this.id = address.getId();
        this.endereco = address.getEndereco();
        this.numero = address.getNumero();
        this.complemento = address.getComplemento();
        this.bairro = address.getBairro();
        this.cidade = address.getCidade();
        this.estado = address.getEstado();
        this.pais = address.getPais();
        this.cep = address.getCep();
    }

    public AddressEntity(Long id, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String pais, String cep, ClientEntity client) {
        this.id = id;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.client = client;
    }

    public AddressEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @JsonProperty("client")
    public ClientEntity returnClientNull() {
        return null;
    }
}
