# Teste TQI

- API para cadastro de clientes


  	Cliente: {nome:"", cpf: "", sexo: "", endereco:{endereco: "", numero:"", complemento:"", cidade:"", estado:"", cep:"", pais:""}}
  	
  	
# Alterações

## Estruturais

- Criação de pacotes para separar responsabilidades
- Alocação de classes em seus respectivos pacotes

## Validacionais

- Inclusão de @NotNull nas propriedades obrigatórias
- Validação de retornos da API

## Código

- Criação de DTO's (Data transfer object) para evitar de usar as Entities mapeadas do banco
- Refatoração dos métodos `getAllClients()` e `getClientById()` que utilizavam as classes repositories na classe controller
- Validação dos dados na rota de `/client/change-address/{id}` assim como na validação dos endereços

## Banco

- A relação entre as tabelas foi invertida, de modo que o `CLIENT` possua a informação do `ADDRESS` 

## Trivia

- Escrevi dois casos de testes
