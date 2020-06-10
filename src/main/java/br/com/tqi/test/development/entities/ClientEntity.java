package br.com.tqi.test.development.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import br.com.tqi.test.development.dto.ClientDTO;

@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private AddressEntity addressEntity;

    public ClientEntity() {
        super();
    }

    public ClientEntity(ClientDTO clientDTO) {
        this.cpf = clientDTO.getCpf();
        this.nome = clientDTO.getNome();
        this.sexo = clientDTO.getSexo();
        this.addressEntity = new AddressEntity(clientDTO.getAddress());
    }

    public ClientEntity(Long id) {
        this.id = id;
    }

    public ClientEntity(Long id, String cpf, String nome, String sexo, AddressEntity addressEntity) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.addressEntity = addressEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }
}
