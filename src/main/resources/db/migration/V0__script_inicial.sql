CREATE TABLE  client(
    id INT AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    sexo VARCHAR(9) NOT NULL,
    id_address INT NOT NULL
);

CREATE INDEX client_cpf ON client(cpf);
ALTER TABLE client ADD CONSTRAINT CLIENT_CPF_UNIQUE UNIQUE(cpf);

CREATE TABLE address(
    id INT AUTO_INCREMENT,
    endereco VARCHAR(255) NOT NULL,
    numero VARCHAR(6),
    complemento VARCHAR(30),
    cep VARCHAR(9) NOT NULL,
    bairro VARCHAR(50),
    estado VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    pais VARCHAR(50)
);

ALTER TABLE client ADD FOREIGN KEY(id_address) REFERENCES address(id);
ALTER TABLE client ADD CONSTRAINT ADDRESS_ID_ADDRESS_UNIQUE UNIQUE(id_address);