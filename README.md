# Gestão de Vagas API

API REST desenvolvida em **Java com Spring Boot**, simulando uma **empresa fictícia de recrutamento e gestão de vagas de trabalho**, onde empresas podem publicar vagas e candidatos podem se candidatar, visualizar oportunidades e gerenciar seu perfil.

O projeto foi desenvolvido com foco em **boas práticas de arquitetura**, **segurança**, **organização de código**, **testes automatizados** e **padrões utilizados em ambientes reais de produção**.

---

## 📌 Sobre o Projeto

A **Gestão de Vagas API** é um sistema backend responsável por:

- Cadastro e autenticação de **empresas**
- Criação e gerenciamento de **vagas de trabalho**
- Cadastro e autenticação de **candidatos**
- Candidatura de candidatos às vagas
- Listagem e filtragem de vagas disponíveis
- Controle de acesso baseado em autenticação e autorização

O projeto simula um cenário real de mercado, abordando desafios comuns em sistemas corporativos, como segurança, escalabilidade, separação de responsabilidades e manutenção do código.

---

## 🏢 Contexto de Negócio

A aplicação representa uma empresa fictícia de recrutamento que atua como intermediária entre **empresas contratantes** e **candidatos**.

### Fluxo principal:
- Empresas se cadastram e autenticam na plataforma
- Empresas publicam vagas de trabalho
- Candidatos se cadastram e autenticam
- Candidatos visualizam e se candidatam às vagas
- O sistema controla permissões e acessos de forma segura

---

## 🧠 Conceitos Aplicados

O projeto foi estruturado seguindo princípios de **Clean Code**, **DDD (Domain-Driven Design)** e **separação de responsabilidades**:

- Controllers focados exclusivamente na camada HTTP
- Use Cases concentrando as regras de negócio
- DTOs para entrada e saída de dados
- Repositories isolando o acesso à base de dados
- Exceções customizadas com tratamento global
- Código modularizado por domínio (Company, Candidate, Job)

Essa abordagem facilita:
- Manutenção do código
- Evolução da aplicação
- Testabilidade
- Escalabilidade

---

## 🔐 Segurança e Autenticação

A API implementa um modelo de segurança robusto baseado em **JWT (JSON Web Token)** e **Spring Security**:

- Autenticação stateless
- Geração e validação de tokens JWT
- Filtros de segurança separados por contexto:
  - Empresa
  - Candidato
- Autorização baseada em perfil
- Proteção de rotas sensíveis
- Arquitetura preparada para ambientes de produção

---

## 🧪 Testes Automatizados

O projeto conta com testes automatizados utilizando:

- **JUnit**
- Testes de Use Cases
- Testes de Controllers
- Classes utilitárias para apoio em testes

Os testes garantem:
- Confiabilidade das regras de negócio
- Segurança nas evoluções do código
- Redução de regressões
- Facilidade de manutenção

---

## 📦 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Maven
- Docker
- PostgreSQL (via Docker)
- Swagger / OpenAPI
- JUnit

---

## 🚀 Executando o Projeto

### Pré-requisitos
- Java 17+
- Docker e Docker Compose
- Maven

### Passos para execução:

```bash
# Subir banco de dados com Docker
docker-compose up -d

# Executar a aplicação
./mvnw spring-boot:run
