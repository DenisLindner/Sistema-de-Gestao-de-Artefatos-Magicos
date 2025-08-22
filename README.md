Problema Proposto pelo ChatGPT para fins de educação

# Sistema da Guilda dos Arcanos

Este projeto é um sistema de gestão para uma **guilda mágica**, onde é possível cadastrar **Magos** e **Artefatos Mágicos**, além de controlar os **empréstimos de artefatos** para cada mago.

O projeto foi desenvolvido em **Java** utilizando **JDBC com PostgreSQL** para persistência dos dados.


## Funcionalidades
- Cadastrar Magos
- Cadastrar Artefatos Mágicos (Combate ou Cura)
- Listar Magos e Artefatos
- Emprestar Artefatos para Magos
- Listar Artefatos de um Mago
- Consultar detalhes de um Artefato

---

## Tecnologias Utilizadas
- **Java 21**
- **PostgreSQL 16**
- **JDBC**
- **Paradigma Orientado a Objetos (POO)**
- **DAO Pattern**


## Estrutura do Projeto
src/

├── dao/

│ ├── ArtefatoDAO.java

│ ├── ArtefatoDAOImpl.java

│ ├── MagoDAO.java

│ └── MagoDAOImpl.java

│

├── main/

│ └── Main.java

│

└── model/

├── ArtefatoMagico.java

├── ArtefatoDeCombate.java

├── ArtefatoDeCura.java

└── Mago.java



## Banco de Dados

### Criação das Tabelas (PostgreSQL)

CREATE DATABASE guilda_arcanos;

CREATE TABLE IF NOT EXISTS mago (
    id_mago INT PRIMARY KEY,
    nome_mago VARCHAR(100) NOT NULL,
    nivel_mago INT NOT NULL
);

CREATE TABLE IF NOT EXISTS artefato (
    codigo_artefato VARCHAR(7) PRIMARY KEY,
    nome_artefato VARCHAR(100) NOT NULL,
    nivel_magia INT NOT NULL,
    descricao TEXT NOT NULL,
    tipo_artefato TEXT NOT NULL,
    id_mago INT NOT NULL,
    CONSTRAINT fk_mago FOREIGN KEY (id_mago) REFERENCES mago(id_mago)
);

## Como Executar
Clone este repositório:

git clone https://github.com/DenisLindner/Sistema-de-Gestao-de-Artefatos-Magicos.git

Configure o banco de dados PostgreSQL com as tabelas acima.

Ajuste a conexão no seu código (URL, usuário e senha).

Compile e execute o projeto:

javac -cp ".;postgresql-42.7.3.jar" main/*.java dao/*.java model/*.java

java -cp ".;postgresql-42.7.3.jar" main.Main

Conceitos Utilizados
Programação Orientada a Objetos (POO)

Herança (ArtefatoMagico → ArtefatoDeCombate / ArtefatoDeCura)

Encapsulamento

Interfaces (DAO)

Implementação (DAOImpl)

Persistência de dados com JDBC

## Autor:
Projeto desenvolvido por Denis Lindner como exercício prático de Java + Banco de Dados.
