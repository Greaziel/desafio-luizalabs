# Desafio Técnico - Luiza Labs

Este projeto foi desenvolvido como parte do desafio técnico da **Luiza Labs**, utilizando **Java** com **Spring Boot**.

---

## 🧭 Visão Geral

O objetivo do desafio é integrar dois sistemas:

- Um **sistema legado** que envia arquivos com dados desnormalizados.
- Um **novo sistema** baseado em uma **API REST**, responsável por processar esses arquivos, armazenar os dados e permitir consultas estruturadas.

A aplicação recebe os arquivos via endpoint REST, realiza o processamento e armazena os dados em um banco de dados **MySQL**.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- MySQL
- Maven
- Swagger / OpenAPI (documentação interativa da API)
- JUnit 5 + Mockito (testes unitários)
- Lombok (eliminação de boilerplate)

---

## 🚀 Funcionalidades

- 📤 Upload de arquivos legados via endpoint `/upload`
- 🔍 Consulta de todos os usuários
- 🔎 Consulta de pedidos por ID de usuário
- 📆 Filtro de pedidos por intervalo de datas
- 🧪 Testes automatizados
- 📝 API documentada com Swagger UI

---

## 📚 Documentação da API

A documentação interativa dos endpoints pode ser acessada em:

http://localhost:8080/swagger-ui.html

### Exemplo:

![Swagger UI](https://github.com/user-attachments/assets/eff72844-daba-48db-8b70-1e8329f63521)

---

## 🧾 Estrutura de Dados

### DTOs

![DTOs](https://github.com/user-attachments/assets/78be3bb8-000f-4f6b-a0aa-42e640e13e59)

### Mapeamento

![Mapeamento](https://github.com/user-attachments/assets/e29d6190-857d-4386-9c62-6926b95cfd6d)

---

## ✅ Boas Práticas Adotadas

- Aplicação de princípios **SOLID**
- Organização modular
- Separação clara de responsabilidades (camadas Controller, Service, DTO)
- Cobertura de testes automatizados
- API REST bem definida e documentada

---

## 👨‍💻 Contato

Desenvolvido por **Greaziel**  

[![LinkedIn](https://img.shields.io/badge/LinkedIn--blue?style=social&logo=linkedin)](https://www.linkedin.com/in/greaziel/)  

[![Email](https://img.shields.io/badge/E--mail-greaziel@hotmail.com-red?style=flat&logo=gmail&logoColor=white)](mailto:greaziel@hotmail.com)

