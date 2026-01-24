# Gestão de Vagas API
> API REST desenvolvida em Java com Spring Boot, focada em arquitetura limpa, segurança com JWT e testes automatizados, simulando um sistema real de gestão de vagas de emprego.


Uma aplicação Spring Boot para gerenciamento de vagas de emprego, conectando candidatos e empresas através de uma API REST completa com autenticação JWT.

## 📋 Descrição

Este projeto implementa um sistema de gestão de vagas de emprego onde:
- **Empresas** podem criar e gerenciar vagas
- **Candidatos** podem se cadastrar, visualizar vagas e se candidatar
- Autenticação e autorização via JWT
- Documentação interativa com Swagger/OpenAPI

O projeto foi desenvolvido com foco em **boas práticas de arquitetura**, **segurança**, **organização de código**, **testes automatizados** e **padrões utilizados em ambientes reais de produção**.

## 📌 Sobre o Projeto

A **Gestão de Vagas API** é um sistema backend responsável por:

- Cadastro e autenticação de **empresas**
- Criação e gerenciamento de **vagas de trabalho**
- Cadastro e autenticação de **candidatos**
- Candidatura de candidatos às vagas
- Listagem e filtragem de vagas disponíveis
- Controle de acesso baseado em autenticação e autorização

O projeto simula um cenário real de mercado, abordando desafios comuns em sistemas corporativos, como segurança, escalabilidade, separação de responsabilidades e manutenção do código.



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

## 🧩 Decisões Técnicas

- Utilização de JWT para autenticação stateless
- Separação de autenticação entre empresa e candidato
- Organização por módulos de domínio
- Uso de Use Cases para centralizar regras de negócio
- Tratamento global de exceções
- Testes focados em regras críticas do domínio

## Este projeto é uma API REST backend, sem interface gráfica, projetada para ser consumida por aplicações frontend ou clientes HTTP.

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (Auth0)**
- **Swagger/OpenAPI**
- **Lombok**
- **JUnit** e **Mockito** (testes)
- **H2 Database** (testes)
- **Maven**
- **Docker & Docker Compose**

## 📦 Pré-requisitos

- Java 17+
- Maven 3.9+
- Docker e Docker Compose (opcional)

## 🚀 Como Executar

### 1. Clonar o Repositório

```bash
git clone <seu-repositorio>
cd gestao_vaga
```

### 2. Configurar Banco de Dados

Com Docker Compose:

```bash
docker-compose up -d
```

Isso iniciará:
- PostgreSQL na porta `5433`
- PgAdmin na porta `5050`

### 3. Executar a Aplicação

```bash
./mvnw spring-boot:run
```

Ou compile e execute:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## 📚 Documentação da API

Acesse a documentação interativa do Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

## 🔐 Autenticação

### Endpoints Públicos

- `POST /company` - Cadastro de empresa
- `POST /company/auth` - Login de empresa
- `POST /candidate` - Cadastro de candidato
- `POST /candidate/auth` - Login de candidato

### Endpoints Protegidos (Requerem JWT)

**Empresas:**
- `POST /company/job/` - Criar vaga

**Candidatos:**
- `GET /candidate/` - Perfil do candidato
- `GET /candidate/job?filter=...` - Listar vagas por filtro
- `POST /candidate/job/apply` - Se candidatar a uma vaga

### Como Usar Token JWT

1. Faça login e receba o token:
```bash
POST /company/auth
{
  "username": "sua_empresa",
  "password": "sua_senha"
}
```

2. Use o token nos headers das requisições protegidas:
```
Authorization: Bearer <seu_token_aqui>
```

## 🧪 Testes

Executar todos os testes:

```bash
./mvnw test
```

Testes disponíveis:
- [`ApplyJobCandidateUseCaseTeste`](src/test/java/br/com/carlosjorge/gestao_vaga/modules/candidate/useCases/ApplyJobCandidateUseCaseTeste.java) - Testes da candidatura em vagas
- [`CreateJobControllersTest`](src/test/java/br/com/carlosjorge/gestao_vaga/modules/company/controllers/CreateJobControllersTest.java) - Testes da criação de vagas

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/carlosjorge/gestao_vaga/
│   │   ├── config/              # Configurações (Swagger, Security)
│   │   ├── exceptions/          # Tratamento de exceções
│   │   ├── modules/
│   │   │   ├── candidate/       # Módulo de candidatos
│   │   │   └── company/         # Módulo de empresas
│   │   ├── providers/           # Provedores (JWT)
│   │   ├── security/            # Filtros de segurança
│   │   └── GestaoVagaApplication.java
│   └── resources/
│       ├── application.properties
│       └── application-test.properties
└── test/
    └── java/br/com/carlosjorge/gestao_vaga/
        ├── modules/
        └── util/
```

## 🔑 Variáveis de Ambiente

Configuradas em `src/main/resources/application.properties`:

```properties
# Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5433/gestao_vaga
spring.datasource.username=admin
spring.datasource.password=admin

# JWT Secrets
security.token.secret=JAVAGAS_@12#
security.token.secret.candidate=JAVAGAS_@12#CANDIDATE
```

## 🐘 Banco de Dados

### Principais Entidades

- **CompanyEntity** - Empresas cadastradas
- **CandidateEntity** - Candidatos cadastrados
- **JobEntity** - Vagas criadas pelas empresas
- **ApplyJobEntity** - Candidaturas de candidatos em vagas

### Migrations

O projeto usa Hibernate DDL auto configurado com `update`. As tabelas são criadas automaticamente na primeira execução.

## 📊 Monitoramento

Endpoints de Health Check:

```
GET /actuator/health
GET /actuator/metrics
```


## 📝 Licença

Este projeto está sob a licença MIT.

## 👨‍💻 Autor

Desenvolvido por Carlos Jorge com Spring Boot.

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Build](https://img.shields.io/badge/build-passing-brightgreen)


## 🛣️ Roadmap

- [ ] Paginação de vagas
- [ ] Cache com Redis
- [ ] Observabilidade com Prometheus e Grafana
- [ ] Deploy em ambiente cloud

---

**Projeto desenvolvido como parte de estudos avançados em Java e Spring Boot, com foco em arquitetura, segurança e boas práticas utilizadas no mercado.** 