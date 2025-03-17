# MarketCar 🚗

## 💻 Como Rodar o Projeto MarketPlace:
Este projeto foi desenvolvido em Kotlin com Spring Boot e utiliza SQLite como banco de dados.

## ✅ Pré-requisitos
Antes de rodar o projeto, certifique-se de ter instalado:
- JDK 17+
- Gradle
- Git
- SQLite

## 📌 Funcionalidades Principais
✔️ Cadastro e autenticação de usuários (JWT Token)
✔️ Cadastro de carros (com status de "Venda" ou "Aluguel")
✔️ Listagem de carros disponíveis
✔️ Atualização de informações do carro
✔️ Remoção de carros
✔️ Gerenciamento de perfis dos usuários

## 🚀 Passos para rodar o projeto

1️⃣ Clone o repositório

```TERMINAL
git clone https://github.com/seu-usuario/MarketPlace.git
```
     
2️⃣ Acesse o diretório do projeto

```TERMINAL
cd MarketPlace
```
3️⃣ Execute o projeto com Gradle

```TERMINAL
./gradlew bootRun  # Linux/macOS
```
```TERMINAL
gradlew.bat bootRun  # Windows
```
4️⃣ A API estará disponível em:

```TERMINAL
http://localhost:8080
```

## ⚡ Banco de Dados (SQLite)
O banco de dados já está incluído no projeto (marketcar.db).
Se precisar visualizar os dados, use um cliente SQLite como:

- DB Browser for SQLite
- SQLite CLI

## 🔐 Autenticação
O projeto usa Spring Security e JWT. Para acessar rotas protegidas, primeiro faça login na rota:

```TERMINAL
POST /api/v1/customers/auth/login
```
Exemplo de corpo da requisição:

```TERMINAL
{
  "name": "seu-nome",
  "password": "sua-senha"
}
```
O token retornado deverá ser usado no Authorization nas requisições protegidas.

