# ThinkDesk - Microsserviço de Chamados

Microsserviço especializado e desacoplado da plataforma **ThinkDesk**, desenvolvido como uma API REST em **Java (Spring Boot 3)** e **MongoDB**. É responsável exclusivamente pelo domínio e gerenciamento do ciclo de vida dos chamados técnicos.

---

## Como Rodar o Projeto

### 1. Pré-requisitos
- JDK 21 ou superior instalado.
- Acesso a uma instância do **MongoDB** (local ou MongoDB Atlas).

### 2. Configurar Variáveis de Ambiente
crie o arquivo `.env` ou definindo diretamente no terminal seguindo o exemplo do env.exemple


### 3. Executar a Aplicação
Pelo terminal na raiz do projeto:

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

### 4. Autenticação (Basic Auth)
- **Usuário:** `admin`
- **Senha:** `suaSenha123`

---

## Endpoints da API

### 1. Abertura de Chamado
Permite que um usuário autenticado abra um chamado informando apenas título e descrição. O sistema registra automaticamente o solicitante autenticado, a data de abertura e o status `ABERTO`.

POST /api/chamados
---

### 2. Listar Chamados do Usuário
Retorna a lista de todos os chamados abertos por um determinado usuário.

GET /api/chamados/usuario/{id} 

---

### 3. Consultar Chamado por ID
Retorna todos os detalhes de um chamado específico pelo seu identificador único.

GET /api/chamados/{id}

---

### 4. Resolver Chamado
Permite que um técnico registre a solução aplicada, alterando o status para `RESOLVIDO`, preenchendo a data de resolução e o nome do técnico autenticado.

PUT /api/chamados/{id}/resolver

---

### Regras de Negócio e Validações

- **Bloqueio de Re-resolução :** Tentativas de alterar ou resolver novamente um chamado que já possua status `RESOLVIDO` são impedidas.
- **Autenticação Obrigatória :** Requisições sem credenciais válidas não têm acesso aos endpoints protegidos.
- **Validação de Campos:** O título deve conter entre 2 e 100 caracteres e a descrição entre 10 e 400 caracteres.
