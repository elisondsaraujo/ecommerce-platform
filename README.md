# E-Commerce Platform

Um projeto robusto de e-commerce construído com **Java Spring Boot**, integrando **PostgreSQL** (SQL) e **MongoDB** (NoSQL), com recursos avançados de autenticação, autorização, análise e gerenciamento de inventário.

## 🚀 Características Principais

### Entidades e Dados
- ✅ **10+ Entidades** com relacionamentos complexos (many-to-many, one-to-many)
- ✅ **Usuários** com 3 tipos de roles: ADMIN, USER, VENDOR
- ✅ **Produtos** com categorias, tags e gerenciamento de estoque
- ✅ **Pedidos** com histórico completo de status
- ✅ **Carrinho** com múltiplos itens
- ✅ **Avaliações** de produtos com ratings
- ✅ **Logs de Inventário** para rastrear movimentação

### Segurança
- ✅ **Autenticação JWT** com tokens access e refresh
- ✅ **Criptografia BCrypt** para senhas
- ✅ **Autorização baseada em roles** (@PreAuthorize)
- ✅ **Variáveis de ambiente** para dados sensíveis
- ✅ **CSRF Protection** desabilitado para APIs REST
- ✅ **Session Stateless** com Spring Security

### Persistência de Dados
- ✅ **PostgreSQL** para dados transacionais (JPA/Hibernate)
- ✅ **MongoDB** para analytics e auditoria
- ✅ **Redis** para cache e sessões
- ✅ **Elasticsearch** para busca full-text
- ✅ **Flyway** para versionamento de migrations

### Analytics e Relatórios
- ✅ **ProductAnalytics** - visualizações, vendas diárias, revenue
- ✅ **OrderAudit** - auditoria completa com histórico de status
- ✅ **UserActivity** - rastreamento de atividades do usuário
- ✅ **Notifications** - sistema de notificações em tempo real

### Funcionalidades Avançadas
- ✅ **Busca por filtros** (nome, categoria, preço)
- ✅ **Paginação** em listagens
- ✅ **Cache com Redis** para melhor performance
- ✅ **Testes unitários e de integração**

## 📦 Stack Tecnológico

```
Backend:
├── Java 17
├── Spring Boot 3.2.0
├── Spring Security + JWT
├── Spring Data JPA
├── Spring Data MongoDB
├── Spring Data Redis
└── MapStruct

Bancos de Dados:
├── PostgreSQL 16
├── MongoDB 7.0
├── Redis 7
└── Elasticsearch 8.10.0

Ferramentas:
├── Maven
├── Docker & Docker Compose
├── Flyway (Migrations)
└── Lombok
```

## 🏗️ Estrutura do Projeto

```
ecommerce-platform/
├── src/main/java/com/ecommerce/
│   ├── config/              # Configurações (Security, DB, Cache)
│   ├── controller/          # REST Controllers (endpoints)
│   ├── service/             # Lógica de negócio
│   ├── repository/          # Acesso a dados JPA
│   ├── repository/mongo/    # Acesso a dados MongoDB
│   ├── model/               # Entidades JPA (SQL)
│   ├── document/            # Documentos MongoDB (NoSQL)
│   ├── dto/                 # Data Transfer Objects
│   ├── exception/           # Exceções customizadas
│   ├── security/            # JWT e autenticação
│   └── util/                # Utilitários
│
├── src/main/resources/
│   ├── application.yml      # Configuração principal
│   ├── application-dev.yml  # Configuração desenvolvimento
│   ├── application-prod.yml # Configuração produção
│   └── db/migration/        # Scripts Flyway
│
├── src/test/                # Testes unitários e integração
├── docker-compose.yml       # Stack completo (Postgres, MongoDB, Redis, ES)
├── .env.example             # Variáveis de ambiente (exemplo)
├── pom.xml                  # Dependências Maven
└── README.md               # Este arquivo
```

## 🔐 Segurança

### Implementações de Segurança

1. **JWT Authentication**
   - Tokens com expiração configurável
   - Refresh tokens para renovação
   - Validação em cada requisição

2. **Password Security**
   - Criptografia BCrypt (strength: 10)
   - Nunca armazenar plain text

3. **Role-Based Access Control (RBAC)**
   ```java
   @PreAuthorize("hasRole('ADMIN')")
   @PreAuthorize("hasRole('VENDOR')")
   @PreAuthorize("hasRole('USER')")
   ```

4. **Environment Variables**
   - Todos os dados sensíveis em `.env`
   - Nunca fazer commit de secrets

5. **CORS & CSRF**
   - CSRF desabilitado (stateless)
   - CORS configurável por ambiente

## 🚀 Como Iniciar

### Pré-requisitos
- Java 17+
- Docker & Docker Compose
- Maven 3.8+

### 1. Clone o repositório
```bash
git clone https://github.com/elisondsaraujo/ecommerce-platform.git
cd ecommerce-platform
```

### 2. Configure variáveis de ambiente
```bash
cp .env.example .env
# Editar .env com suas configurações
```

### 3. Inicie os bancos de dados
```bash
docker-compose up -d
```

### 4. Compile e execute
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080/api`

## 📚 Endpoints da API

### Autenticação
```
POST   /api/auth/register      - Registrar novo usuário
POST   /api/auth/login         - Login e obter JWT token
POST   /api/auth/refresh       - Renovar token
POST   /api/auth/logout        - Logout
```

### Produtos
```
GET    /api/products           - Listar produtos (paginado)
GET    /api/products/{id}      - Detalhes do produto
GET    /api/products/search    - Buscar produtos
POST   /api/products           - Criar produto (ADMIN)
PUT    /api/products/{id}      - Atualizar produto (ADMIN)
DELETE /api/products/{id}      - Deletar produto (ADMIN)
```

### Categorias
```
GET    /api/categories         - Listar categorias
GET    /api/categories/{id}    - Detalhes da categoria
POST   /api/categories         - Criar categoria (ADMIN)
PUT    /api/categories/{id}    - Atualizar categoria (ADMIN)
DELETE /api/categories/{id}    - Deletar categoria (ADMIN)
```

### Pedidos
```
GET    /api/orders            - Meus pedidos
GET    /api/orders/{id}       - Detalhes do pedido
POST   /api/orders            - Criar novo pedido
PUT    /api/orders/{id}       - Atualizar status (ADMIN)
```

### Carrinho
```
GET    /api/cart              - Ver carrinho
POST   /api/cart/items        - Adicionar item
PUT    /api/cart/items/{id}   - Atualizar quantidade
DELETE /api/cart/items/{id}   - Remover item
DELETE /api/cart              - Limpar carrinho
```

### Avaliações
```
GET    /api/reviews           - Listar reviews
GET    /api/products/{id}/reviews - Reviews do produto
POST   /api/reviews           - Criar review
PUT    /api/reviews/{id}      - Atualizar review
DELETE /api/reviews/{id}      - Deletar review
```

### Admin
```
GET    /api/admin/analytics   - Dashboard com estatísticas
GET    /api/admin/orders      - Todos os pedidos
GET    /api/admin/users       - Todos os usuários
GET    /api/admin/inventory   - Logs de inventário
```

## 🔑 Exemplo de Autenticação

### 1. Registrar usuário
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@example.com",
    "password": "senha123",
    "firstName": "João",
    "lastName": "Silva"
  }'
```

### 2. Fazer login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@example.com",
    "password": "senha123"
  }'

# Resposta:
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9...",
  "refreshToken": "eyJhbGciOiJIUzUxMiJ9...",
  "tokenType": "Bearer"
}
```

### 3. Usar o token em requisições
```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..."
```

## 🗄️ Modelos de Dados

### User (PostgreSQL)
- id (PK)
- email (UNIQUE)
- password (encrypted)
- firstName, lastName
- phone, address, city, state, country
- role (ADMIN, USER, VENDOR)
- active, emailVerified
- createdAt, updatedAt

### Product (PostgreSQL)
- id (PK)
- name, description
- price, discountPrice
- quantity, sold
- sku, imageUrl
- categoryId (FK)
- tags (M2M)
- active
- createdAt, updatedAt

### Order (PostgreSQL)
- id (PK)
- orderNumber (UNIQUE)
- userId (FK)
- totalPrice, taxAmount, discountAmount, finalPrice
- status (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED, REFUNDED)
- shippingAddress, billingAddress
- trackingNumber
- items (1:M)
- createdAt, updatedAt

### ProductAnalytics (MongoDB)
- productId
- totalViews, totalSold
- averageRating, reviewCount
- dailyMetrics (array com views, sales, revenue)
- lastUpdated

### OrderAudit (MongoDB)
- orderId, orderNumber, userId
- totalAmount, status
- statusHistory (array com mudanças e motivos)
- createdAt

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar teste específico
mvn test -Dtest=UserServiceTest

# Com relatório de cobertura
mvn test jacoco:report
```

## 📊 Monitoramento e Logs

- **Logs**: Configurados em `application.yml`
- **Level**: INFO (produção), DEBUG (desenvolvimento)
- **Analytics**: MongoDB com dados em tempo real
- **Redis Cache**: Melhora performance de queries frequentes

## 🐳 Docker Compose Services

```yaml
PostgreSQL 16    -> localhost:5432
MongoDB 7.0      -> localhost:27017
Redis 7          -> localhost:6379
Elasticsearch 8  -> localhost:9200
```

## 📋 Variáveis de Ambiente (.env)

```env
# JWT
JWT_SECRET=sua-chave-secreta-muito-longa-e-complexa
JWT_EXPIRATION=86400000
JWT_REFRESH_EXPIRATION=604800000

# Database
DB_HOST=localhost
DB_PORT=5432
DB_NAME=ecommerce_db
DB_USER=ecommerce_user
DB_PASSWORD=sua-senha-segura

# MongoDB
MONGO_URI=mongodb://user:pass@localhost:27017/ecommerce_db

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=sua-senha-redis
```

## 🛠️ Troubleshooting

### Erro: "Connection refused" no PostgreSQL
```bash
docker-compose ps
docker-compose logs postgres
```

### Erro: Porta já em uso
```bash
# Mudar porta em docker-compose.yml ou
lsof -i :5432  # Encontrar o processo
kill -9 <PID>   # Encerrar
```

### Erro: JWT inválido
- Verificar se o secret em `.env` está correto
- Verificar expiração do token
- Fazer novo login

## 📝 Roadmap Futuro

- [ ] Sistema de pagamento (Stripe/PayPal)
- [ ] Notifications em tempo real (WebSocket)
- [ ] Admin Dashboard (React/Vue)
- [ ] Mobile App (React Native)
- [ ] Search avançada com Elasticsearch
- [ ] Recomendações com Machine Learning
- [ ] Multi-idioma (i18n)
- [ ] Rate limiting por IP

## 👥 Contribuição

Pull requests são bem-vindos! Para mudanças maiores, abra uma issue primeiro.

## 📄 Licença

MIT License - veja LICENSE.md para detalhes

## 📧 Contato

- GitHub: [@elisondsaraujo](https://github.com/elisondsaraujo)
- Email: contato@ecommerce.com

---

**Última atualização**: Maio 2026
**Versão**: 1.0.0
