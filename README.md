# Projeto Desafio Técnico Luiza Labs

Este projeto foi desenvolvido para o desafio técnico da Luiza Labs utilizando **Java** com **Spring Boot**.

---

## Visão Geral

O objetivo deste desafio é integrar dois sistemas:

- Um sistema legado que envia arquivos contendo dados desnormalizados.
- Um novo sistema baseado em uma API REST para processar esses arquivos, armazenar os dados e disponibilizar consultas.

A aplicação recebe arquivos via endpoint REST, processa o conteúdo e armazena os dados em um banco de dados **MySQL**.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- MySQL
- Swagger / OpenAPI para documentação da API
- JUnit 5 e Mockito para testes unitários e mocks
- Lombok para reduzir boilerplate
- Maven como gerenciador de dependências

---

## Funcionalidades Principais

- Upload e processamento de arquivos legados via endpoint `/upload`
- Consultas dos dados armazenados via endpoints RESTful
- Filtro de dados por intervalo de datas
- Documentação automática dos endpoints via Swagger UI
- Testes automatizados garantindo qualidade e cobertura do código

---

## Documentação da API

A documentação interativa está disponível em:

http://localhost:8080/swagger-ui.html


![image](https://github.com/user-attachments/assets/eff72844-daba-48db-8b70-1e8329f63521)

Classes de DTO

![image](https://github.com/user-attachments/assets/78be3bb8-000f-4f6b-a0aa-42e640e13e59)

Mapeamento

![image](https://github.com/user-attachments/assets/e29d6190-857d-4386-9c62-6926b95cfd6d)


Boas práticas
Segue princípios SOLID e design modular.

Testes automatizados para garantir qualidade.

API documentada para facilitar integrações.

Contato
Desenvolvido por Greaziel
