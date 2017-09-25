CREATE DATABASE SeedShare;
USE SeedShare;

CREATE TABLE Solicitacao (
quantidade INTEGER,
dataSolicitacao DATE,
idSolicitacao INTEGER PRIMARY KEY,
idOferta INTEGER,
idUsuario INTEGER
);

CREATE TABLE Clima (
nome VARCHAR(50),
descricao VARCHAR(50),
idClima INTEGER PRIMARY KEY
);

CREATE TABLE UsuarioSelo (
idUsuarioSelo INTEGER PRIMARY KEY,
pontuacao INTEGER,
idUsuario INTEGER,
idSelo INTEGER
);

CREATE TABLE Oferta (
tipo INTEGER,
precoUnidade DECIMAL(10,2),
dataCriacao DATE,
quantidade INTEGER,
idOferta INTEGER PRIMARY KEY,
statusOferta INTEGER,
idUsuario INTEGER,
idEspecie INTEGER
);

CREATE TABLE Fruto (
consumoFauna BOOLEAN,
dataInicioFrutificacao DATE,
dataFimFrutificacao DATE,
consumoHumano BOOLEAN,
idFrutificacao INTEGER PRIMARY KEY,
nomeFruto VARCHAR(50),
descricao TEXT,
idEspecie INTEGER
);

CREATE TABLE EspecieClima (
idEspecie INTEGER,
idClima INTEGER,
FOREIGN KEY(idClima) REFERENCES Clima (idClima)
);

CREATE TABLE EspecieSolo (
idEspecie INTEGER,
idSolo INTEGER
);

CREATE TABLE Permissao (
nome VARCHAR(50),
idPermissao INTEGER PRIMARY KEY
);

CREATE TABLE Selo (
nome VARCHAR(50),
urlImg VARCHAR(500),
idSelo INTEGER PRIMARY KEY,
categoria INTEGER,
descricao VARCHAR(250),
pontuacaoNecessaria INTEGER
);

CREATE TABLE UsuarioPermissao (
idUsuario INTEGER,
idPermissao INTEGER,
FOREIGN KEY(idPermissao) REFERENCES Permissao (idPermissao)
);

CREATE TABLE Endereco (
latitude DECIMAL(10,8),
dataCriacao DATE,
longitude DECIMAL(11,6),
idEndereco INTEGER PRIMARY KEY,
idUsuario INTEGER
);

CREATE TABLE Sugestao (
idSugestao INTEGER PRIMARY KEY,
idUsuario INTEGER,
idEspecie INTEGER
);

CREATE TABLE Especie (
dataInsercao DATE,
nomeCientifico VARCHAR(100),
ornamental BOOLEAN,
medicinal BOOLEAN,
melifera BOOLEAN,
nomePopular VARCHAR(100),
aprovada BOOLEAN,
porte DECIMAL,
idEspecie INTEGER PRIMARY KEY,
atraiPassaros BOOLEAN,
descricao TEXT,
guiaCultivo TEXT,
fotoUrl VARCHAR(200),
idCrescimento INTEGER
);

CREATE TABLE Crescimento (
descricao VARCHAR(50),
idCrescimento INTEGER PRIMARY KEY
);

CREATE TABLE Flor (
nome VARCHAR(50),
dataInicioFloracao DATE,
dataFimFloracao DATE,
idFlor INTEGER PRIMARY KEY,
cor VARCHAR(20),
idEspecie INTEGER,
aromatica BOOLEAN,
descricao VARCHAR(250),
FOREIGN KEY(idEspecie) REFERENCES Especie (idEspecie)
);

CREATE TABLE Solo (
nome VARCHAR(50),
descricao VARCHAR(50),
idSolo INTEGER PRIMARY KEY
);

CREATE TABLE Usuario (
senha VARCHAR(100),
email VARCHAR(100),
tipo INTEGER,
idUsuario INTEGER PRIMARY KEY,
CPF VARCHAR(11),
fotoUrl VARCHAR(200)
);

CREATE TABLE Floricultura (
CNPJ VARCHAR(14),
logoURL VARCHAR(200),
descricao VARCHAR(250),
nome VARCHAR(50),
idEmpresa INTEGER PRIMARY KEY,
idUsuario INTEGER,
FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario)
);

ALTER TABLE Solicitacao ADD FOREIGN KEY(idOferta) REFERENCES Oferta (idOferta);
ALTER TABLE Solicitacao ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE UsuarioSelo ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE UsuarioSelo ADD FOREIGN KEY(idSelo) REFERENCES Selo (idSelo);
ALTER TABLE Oferta ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE Oferta ADD FOREIGN KEY(idEspecie) REFERENCES Especie (idEspecie);
ALTER TABLE Fruto ADD FOREIGN KEY(idEspecie) REFERENCES Especie (idEspecie);
ALTER TABLE EspecieClima ADD FOREIGN KEY(idEspecie) REFERENCES Especie (idEspecie);
ALTER TABLE EspecieSolo ADD FOREIGN KEY(idEspecie) REFERENCES Especie (idEspecie);
ALTER TABLE EspecieSolo ADD FOREIGN KEY(idSolo) REFERENCES Solo (idSolo);
ALTER TABLE UsuarioPermissao ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE Endereco ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE Sugestao ADD FOREIGN KEY(idUsuario) REFERENCES Usuario (idUsuario);
ALTER TABLE Sugestao ADD FOREIGN KEY(idEspecie) REFERENCES Especie (idEspecie);
ALTER TABLE Especie ADD FOREIGN KEY(idCrescimento) REFERENCES Crescimento (idCrescimento);

