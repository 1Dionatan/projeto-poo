# Projeto banco digital🏦

O objetivo do projeto é compreender conceitos de comunicação na web, entender de vez conceitos como interfaces no java para atingir abstração e uma breve aplicação de conceitos de banco de dados. Um projeto muito completo que certamente lhe agregará como profissional.

🎯Funcionalidades do sistema

* Deverá intercambiar seu tema visual, a aplicação poderá ser utilizada no console ou em uma interface visual utilizando java swing.
* Deverá permitir a persistência dos dados em um banco fictício(List<Dados>) ou em um banco real utilizando [supabase](https://supabase.com/).
* Deverá permitir ao usuário realizar cadastramento e login.
* Deverá prover a funcionalidade de consulta de boleto.
* Deverá prover a funcionalidade de pagamento de boleto.
* Deverá prover a funcionalidade de geração de QR Code PIX.

📚 Processo

A cada aula estaremos evoluindo em sala cada um desses pontos, a nota integral do projeto se dá pela entrega completa do mesmo, onde o principal desafio e com maior peso de nota é desenvolver sozinho a funcionalidade de geração do PIX e persistência do mesmo, claro após compreendido os conhecimentos transmitidos em sala.

Ao fim desse projeto, você **compreenderá**:
* Interfaces no Java
* Conceitos intermediários de comunicação na web
* Conceitos básicos de banco de dados
* Conceitos de criação de interfaces visuais simples no Java

---

## Primeiro guia - Estruturação do projeto🗃

Primeiro passo é criar a estrutura de pastas e seus respectivos arquivos.

## Criação da estrutura de pastas📂

Primeiro vamos no nosso repositório particular `projeto-poo`, não confunda com o repositório compartilhado `poo-terceiro-periodo`, vamos criar a pasta `segundob` caso ela não exista. Nessa pasta vamos criar uma pasta chamada `bankingapp` e mais uma pasta dentro chamada `src`.

Dentro de `src` vamos criar as pastas:
* domain
* infra
* service

Na pasta `domain`, vamos ter as pastas:
* dto
* repositories

Na pasta `infra`, vamos ter as pastas:
* console
* testdb

Não termos em um primeiro momento pastas dentro de `service`.

Ao final você terá uma estrutura de pastas similar a imagem abaixo:
![Project folder structure](https://github.com/user-attachments/assets/9f237e21-0f0d-4b28-94f8-85f778bda702)

Aqui está a estrutura de pastas da imagem, representada em Markdown:

```markdown
projeto-poo/
└── src/
    ├── primeirob/
    └── segundob/
        └── bankingapp/
            └── src/
                ├── domain/
                │   ├── dto/
                │   │   ├── LoginDTO.java
                │   │   └── UserAccountDTO.java
                │   └── repositories/
                │       ├── IUserInterface.java
                │       └── IUserRepository.java
                ├── infra/
                │   ├── console/
                │   │   └── ConsoleUI.java
                │   └── testdb/
                │       └── UserTestDBRepository.java
                ├── service/
                │   ├── Application.java
                │   ├── BankingService.java
                │   └── UserService.java
                └── Main.java
```

Claro,  faltam criar os arquivos😅

---

## Criação das classes📄

### Começando pela pasta `domain`

Pasta `dto` com as classes:
- **LoginDTO**
- - String document
- - String password
- **UserAccountDTO**
- - String id
- - String document
- - String name
- - String email
- - String document
- - LocalDateTime createAt
- - LocalDateTime disabledAt

Pasta `repositories` com as interfaces:
- **IUserInterface**
- - Integer showInitialScreenMenu();
- - Integer showHomeMenu(String userName);
- - LoginDTO getLoginData();
- - UserAccountDTO getCreateUserData();
- - void showErrorMsg(String msg);
- - void showExitMessage();
- **IUserRepository**
- - UserAccountDTO createUser(UserAccountDTO dto);
- - UserAccountDTO findUserBy(String document);

---

### Pasta `infra`

Pasta `console` com a classe:
- **ConsoleUI**: Ele implementa a interface `IUserInterface` e precisa criar os métodos definidos nesse contrato, com isso precisamos implementar cada um os métodos no contexto de receber/apresentar os dados para o usuário via console, exemplo:

![ConsoleUI](https://github.com/user-attachments/assets/1c25431d-d52e-493e-998a-95bba93c5270)

⚠ **Finalize as implementações necessárias onde estão os comentários!**

Pasta `testdb` com a classe:
- **UserTestDBRepository**: Ele implementa a interface `IUserRepository` e precisa criar os métodos definidos nesse contrato, com isso precisamos implementar cada um os métodos no contexto de simular um banco de dados em memória, exemplo:

![Dbtest](https://github.com/user-attachments/assets/75d1e3f4-6d33-4fe2-9806-a31de315f3f8)

⚠ **Finalize as implementações necessárias onde estão os comentários!**

---

### Pasta `service`

- **Application**

![application](https://github.com/user-attachments/assets/86b49b53-9c05-4884-b3eb-d8fc4bf92052)

- **BankingService**

![bankingservice](https://github.com/user-attachments/assets/25572788-d58b-41d6-b2ff-df8c22129081)

- **UserService**

![userservice](https://github.com/user-attachments/assets/abd7a6ad-b927-41e4-bb86-c55882d33ec5)

### No diretório raiz em `src`

- **Main**

![main](https://github.com/user-attachments/assets/b01e12cf-3312-40f7-bbf4-21862b165599)

---

## Desafio🏋️

Crie os classes faltantes e finalize a implementação dos métodos das classes que implementam as interfaces criadas.