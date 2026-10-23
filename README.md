# ThinkDesk - API de Chamados

API REST desenvolvida em **Java** com **Spring Boot 3** e **MongoDB** para gerenciamento do ciclo de vida de chamados técnicos.

---

## 🛠️ Tecnologias

- **Java 23**
- **Spring Boot 3.4.0**
- **Spring Data MongoDB**
- **Spring Security** (Basic Auth & CSRF desabilitado para REST)
- **Maven**

---

## 🚀 Como Rodar o Projeto

### 1. Pré-requisitos
- JDK 21 ou superior instalado.
- Acesso a uma instância do **MongoDB** (local ou MongoDB Atlas).

### 2. Configurar Variáveis de Ambiente
No arquivo `.env` ou definindo diretamente no terminal:

```env
MONGODB_URI=mongodb+srv://<usuario>:<senha>@<cluster>.mongodb.net/thindesk
MONGODB_NAME=thindesk
```

### 3. Executar a Aplicação
Pelo terminal na raiz do projeto:

```powershell
# No Windows PowerShell:
$env:MONGODB_URI="sua_string_de_conexao/thindesk"; $env:MONGODB_NAME="thindesk"; .\mvnw.cmd spring-boot:run
```

Ou execute a classe principal pela sua IDE (VS Code / IntelliJ):
`com.pfc.thinkdeskChamado.ThinkdeskChamadoApplication`

A API estará disponível em: `http://localhost:8080`

### 4. Autenticação (Basic Auth)
Todas as requisições aos endpoints da API requerem autenticação **HTTP Basic**:
- **Usuário:** `admin`
- **Senha:** `suaSenha123`

---

## 📌 Endpoints da API

### 1. Abertura de Chamado
Permite que um usuário autenticado abra um chamado informando apenas título e descrição. O sistema registra automaticamente o solicitante autenticado, a data de abertura e o status `ABERTO`.

- **Método:** `POST`
- **URL:** `/api/chamados`
- **Headers:** `Content-Type: application/json`
- **Corpo da Requisição (Body):**
  ```json
  {
    "titulo": "Computador não liga",
    "descricao": "O monitor acende mas o computador não inicia o sistema."
  }
  ```
- **Resposta de Sucesso (`201 Created`):**
  ```json
  {
    "id": "6adac6334b8951034b86a591",
    "titulo": "Computador não liga",
    "descricao": "O monitor acende mas o computador não inicia o sistema.",
    "status": "ABERTO",
    "usuario": "admin",
    "dataAbertura": "2026-10-22T23:28:03.0619188"
  }
  ```

---

### 2. Listar Chamados do Usuário
Retorna a lista de todos os chamados abertos por um determinado usuário.

- **Método:** `GET`
- **URL:** `/api/chamados/usuario/{id}` *(onde `{id}` é o login/nome do usuário, ex: `admin`)*
- **Resposta de Sucesso (`200 OK`):**
  ```json
  [
    {
      "id": "6adac6334b8951034b86a591",
      "titulo": "Computador não liga",
      "status": "ABERTO",
      "usuario": "admin",
      "tecnico": null,
      "dataAbertura": "2026-10-22T23:28:03.0619188",
      "dataResolucao": null
    }
  ]
  ```

---

### 3. Consultar Chamado por ID
Retorna todos os detalhes de um chamado específico pelo seu identificador único.

- **Método:** `GET`
- **URL:** `/api/chamados/{id}` *(ex: `/api/chamados/6adac6334b8951034b86a591`)*
- **Resposta de Sucesso (`200 OK`):**
  ```json
  {
    "id": "6adac6334b8951034b86a591",
    "titulo": "Computador não liga",
    "descricao": "O monitor acende mas o computador não inicia o sistema.",
    "status": "ABERTO",
    "usuario": "admin",
    "tecnico": null,
    "solucao": null,
    "dataAbertura": "2026-10-22T23:28:03.0619188",
    "dataResolucao": null
  }
  ```
- **Resposta de Erro (`404 Not Found`):** caso o ID não exista no banco.

---

### 4. Resolver Chamado
Permite que um técnico registre a solução aplicada, alterando o status para `RESOLVIDO`, preenchendo a data de resolução e o nome do técnico autenticado.

- **Método:** `PUT`
- **URL:** `/api/chamados/{id}/resolver` *(ex: `/api/chamados/6adac6334b8951034b86a591/resolver`)*
- **Headers:** `Content-Type: application/json`
- **Corpo da Requisição (Body):**
  ```json
  {
    "solucao": "Substituída a fonte de alimentação. Equipamento testado e operando normalmente."
  }
  ```
- **Resposta de Sucesso (`200 OK`):**
  ```json
  {
    "id": "6adac6334b8951034b86a591",
    "titulo": "Computador não liga",
    "descricao": "O monitor acende mas o computador não inicia o sistema.",
    "status": "RESOLVIDO",
    "usuario": "admin",
    "tecnico": "admin",
    "solucao": "Substituída a fonte de alimentação. Equipamento testado e operando normalmente.",
    "dataAbertura": "2026-10-22T23:28:03.0619188",
    "dataResolucao": "2026-10-22T23:32:15.8921102"
  }
  ```

---

### 🔒 Regras de Negócio e Validações

- **Bloqueio de Re-resolução (`400 Bad Request`):** Tentativas de alterar ou resolver novamente um chamado que já possua status `RESOLVIDO` são impedidas.
- **Autenticação Obrigatória (`401 Unauthorized`):** Requisições sem credenciais válidas não têm acesso aos endpoints protegidos.
- **Validação de Campos:** O título deve conter entre 2 e 100 caracteres e a descrição entre 10 e 400 caracteres.
