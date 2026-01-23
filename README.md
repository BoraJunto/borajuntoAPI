# BoraJunto API

## Descrição
API REST para um aplicativo de caronas entre universitários. Permite que usuários criem, busquem e compartilhem caronas, além de gerenciar avaliações entre passageiros e motoristas.

## Funcionalidades Principais
- ✅ Gerenciamento de Usuários (Registro, Login, Perfil)
- ✅ Autenticação com JWT
- ✅ Criação e gerenciamento de Caronas
- ✅ Solicitação e aprovação de Passageiros
- ✅ Sistema de Avaliações entre usuários
- ✅ Busca avançada de caronas por origem, destino e período
- ✅ Controle de vagas disponíveis
- ✅ Histórico completo de transações

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **MySQL 8.0**
- **Lombok**
- **Maven**

## Requisitos
- Java 17 ou superior
- MySQL 8.0 ou superior
- Maven 3.6+

## Instalação

### 1. Clonar o repositório
```bash
git clone https://github.com/BoraJunto/borajuntoAPI.git
cd borajuntoAPI/projetobora
```

### 2. Configurar banco de dados
Crie um banco de dados MySQL:
```sql
CREATE DATABASE borajunto;
```

### 3. Atualizar application.properties
Abra `src/main/resources/application.properties` e configure:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/borajunto
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
jwt.secret=sua_chave_secreta_muito_segura_aqui_minimo_32_caracteres
```

### 4. Compilar e executar
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## Estrutura do Projeto

```
projetobora/
├── src/main/java/com/borajunto/projetobora/
│   ├── config/              # Configurações da aplicação
│   ├── controler/           # Controllers REST
│   ├── dto/                 # Data Transfer Objects
│   ├── entidade/            # Entidades JPA
│   ├── repository/          # Repositórios JPA
│   ├── service/             # Lógica de negócio
│   ├── util/                # Utilitários (JWT, etc)
│   └── ProjetoboraApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## Endpoints da API

### Autenticação
- `POST /api/auth/login` - Login do usuário
- `POST /api/auth/validar` - Validar token JWT

### Usuários
- `GET /api/usuarios` - Listar todos os usuários
- `GET /api/usuarios/{cpf}` - Obter usuário por CPF
- `POST /api/usuarios/registrar` - Registrar novo usuário
- `PUT /api/usuarios/{cpf}` - Atualizar usuário
- `DELETE /api/usuarios/{cpf}` - Deletar usuário

### Caronas
- `GET /api/caronas` - Listar todas as caronas
- `GET /api/caronas/ativas` - Listar caronas ativas
- `GET /api/caronas/{id}` - Obter carona por ID
- `GET /api/caronas/buscar?origem=X&destino=Y` - Buscar caronas
- `GET /api/caronas/motorista/{cpf}` - Caronas de um motorista
- `GET /api/caronas/periodo?dataInicio=X&dataFim=Y` - Buscar por período
- `POST /api/caronas` - Criar nova carona
- `PUT /api/caronas/{id}` - Atualizar carona
- `PUT /api/caronas/{id}/cancelar` - Cancelar carona
- `DELETE /api/caronas/{id}` - Deletar carona

### Passageiros
- `GET /api/passageiros` - Listar todos os passageiros
- `GET /api/passageiros/{id}` - Obter passageiro
- `GET /api/passageiros/carona/{caronaId}` - Passageiros de uma carona
- `GET /api/passageiros/usuario/{cpf}` - Caronas de um usuário
- `GET /api/passageiros/status/{status}` - Filtrar por status
- `POST /api/passageiros/solicitar/{caronaId}/{cpfUsuario}` - Solicitar vaga
- `PUT /api/passageiros/{id}/aprovar` - Aprovar passageiro
- `PUT /api/passageiros/{id}/rejeitar` - Rejeitar passageiro
- `PUT /api/passageiros/{id}/cancelar` - Cancelar passageiro
- `DELETE /api/passageiros/{id}` - Deletar passageiro

### Avaliações
- `GET /api/avaliacoes` - Listar todas as avaliações
- `GET /api/avaliacoes/{id}` - Obter avaliação
- `GET /api/avaliacoes/carona/{caronaId}` - Avaliações de uma carona
- `GET /api/avaliacoes/usuario/{cpf}` - Avaliações de um usuário
- `GET /api/avaliacoes/media/{cpf}` - Média de avaliações de um usuário
- `POST /api/avaliacoes` - Criar nova avaliação
- `PUT /api/avaliacoes/{id}` - Atualizar avaliação
- `DELETE /api/avaliacoes/{id}` - Deletar avaliação

## Exemplo de Uso

### 1. Registrar um novo usuário
```bash
curl -X POST http://localhost:8080/api/usuarios/registrar \
  -H "Content-Type: application/json" \
  -d '{
    "cpf": "12345678900",
    "nome": "João Silva",
    "login": "joao.silva",
    "senha": "senha123",
    "email": "joao@example.com",
    "telefone": "11999999999",
    "universidade": "USP",
    "rA": "12345678",
    "sexo": "M"
  }'
```

### 2. Realizar login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "login": "joao.silva",
    "senha": "senha123"
  }'
```

### 3. Criar uma carona
```bash
curl -X POST http://localhost:8080/api/caronas \
  -H "Content-Type: application/json" \
  -d '{
    "cpfMotorista": "12345678900",
    "origem": "Avenida Paulista, São Paulo",
    "destino": "Rua Oscar Freire, São Paulo",
    "dataHoraSaida": "2025-02-01T14:00:00",
    "vagas": 4,
    "valor": 20.00,
    "descricao": "Carona tranquila e segura"
  }'
```

### 4. Buscar caronas
```bash
curl http://localhost:8080/api/caronas/buscar?origem=Avenida%20Paulista&destino=Rua%20Oscar%20Freire
```

### 5. Solicitar uma vaga
```bash
curl -X POST http://localhost:8080/api/passageiros/solicitar/1/12345678900
```

### 6. Avaliar um usuário
```bash
curl -X POST http://localhost:8080/api/avaliacoes \
  -H "Content-Type: application/json" \
  -d '{
    "caronaId": 1,
    "cpfAvaliador": "12345678900",
    "cpfAvaliado": "98765432100",
    "nota": 5,
    "comentario": "Ótimo motorista, muito educado!"
  }'
```

## Status de Desenvolvimento

- [x] CRUD de Usuários
- [x] Autenticação JWT
- [x] CRUD de Caronas
- [x] CRUD de Passageiros
- [x] Sistema de Avaliações
- [x] Validações básicas
- [x] Tratamento de erros
- [ ] Testes unitários
- [ ] Frontend web (em desenvolvimento)
- [ ] App mobile (planejado)

## Segurança

- Senhas são criptografadas com BCrypt
- Autenticação via JWT (JSON Web Tokens)
- Validação de entrada de dados
- Tratamento adequado de exceções

## Contribuindo

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Contato

- GitHub: [@BoraJunto](https://github.com/BoraJunto)

## Próximos Passos

- Implementar frontend em React/Vue.js
- Criar aplicativo mobile com Flutter
- Adicionar sistema de pagamento
- Implementar chat em tempo real
- Adicionar mais critérios de busca (horário, preço, etc)
- Implementar notificações push

---

**Desenvolvido com ❤️ para simplificar as caronas entre universitários**
