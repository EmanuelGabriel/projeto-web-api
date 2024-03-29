-- CRIANDO A TABELA PESSOA

CREATE TABLE TB_PESSOA(
	ID INT NOT NULL PRIMARY KEY,
	NOME VARCHAR(100) NOT NULL,
	CPF VARCHAR(12) NOT NULL,
	IDADE INT NOT NULL,
	GENERO INT NOT NULL
);


-- INSERINDO CARGA DE DADOS
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (1, 'Fernando Souza Costa', '04984727355', 28, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (2, 'Miriam Cardoso Brito', '02187652344', 39, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (3, 'Pedro Alves Cabral', '08713467823', 22, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (4, 'Júlio Cesar Amorin', '71263489721', 29, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (5, 'Silvia Costa Amaral Pinto', '04066582377', 46, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (6, 'Bruno Cabral de Oliveira Neto', '09081255278', 36, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (7, 'Samara Ferreira da Costa', '07089328623', 31, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (8, 'Luana Rofel Martins', '37836283744', 21, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (9, 'Francisco das Chagas Borges', '01054925070', 22, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (10, 'Ricardo Iago André das Neves', '17683128007', 39, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (11, 'Patrícia Cristiane Sophia das Neves', '43842152078', 57, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (12, 'Manuel Otávio Nicolas Oliveira', '10974373028', 17, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (13, 'Mário Luiz Melo', '22160984000', 36, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (14, 'Aline Nicole Luciana Carvalho', '15510210052', 47, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (15, 'Aurora Rayssa Drumond', '30903806096', 51, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (16, 'Joaquim Sebastião Martins', '43629389074', 66, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (17, 'Vicente Ricardo Murilo Teixeira', '05613467832', 78, 0);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (18, 'Cláudia Rebeca Mendes', '97113947077', 69, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (19, 'Antônia Eloá Farias', '68867750062', 55, 1);
INSERT INTO TB_PESSOA (ID, NOME, CPF, IDADE, GENERO) VALUES (20, 'Brenda Emanuelly Rita Mendes', '49045280000', 31, 1);

