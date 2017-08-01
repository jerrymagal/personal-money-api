CREATE TABLE IF NOT EXISTS pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(150),
	numero VARCHAR(10),
	complemento VARCHAR(50),
	bairro VARCHAR(60),
	cep VARCHAR(8),
	cidade VARCHAR(100),
	estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Alexandre Rolim', true, 'Rua Cândido Mendes', '16', 'apto 1003', 'Glória', '20241220', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Vivin Castro', true, 'Rua Cândido Mendes', '16', 'apto 1003', 'Glória', '20241220', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Marisa Rolim', true, 'Rua Carlos Matoso Correia', '322', 'apto 401', 'Benfica', '20911390', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Joel Rolim', true, 'Rua Carlos Matoso Correia', '322', 'apto 401', 'Benfica', '20911390', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Teresa Rolim', true, 'Rua Balanita', '66', 'apto 301', 'Benfica', '20222360', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Maria Gueivara', true, 'Rua Senador Vergueiro', '148', 'apto 1201', 'Flamengo', '22230001', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Urias de Castro', true, 'Rua Senador Vergueiro', '148', 'apto 1201', 'Flamengo', '22230001', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Jorge Almeida', true, 'Rua Balanita', '66', 'apto 301', 'Benfica', '20222360', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Felipe Joe Rolim', true, 'Rua Balanita', '66', 'apto 301', 'Benfica', '20222360', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Riquinho', true, 'Rua Cândido Mendes', '16', 'apto 1003', 'Glória', '20241220', 'Rio de Janeiro', 'RJ');
	
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
	VALUES ('Lindinha', true, 'Rua Cândido Mendes', '16', 'apto 1003', 'Glória', '20241220', 'Rio de Janeiro', 'RJ');
	
	
