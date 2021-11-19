
CREATE TABLE IF NOT EXISTS PAUTA (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  descricao VARCHAR(250) NOT NULL,
  data_inicio_votacao TIMESTAMP NULL,
  data_fim_votacao TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS VOTACAO_PAUTA (
 id_pauta  integer not null,
 id_associado varchar(250) not null,
 voto varchar(3) not null,
 primary key(id_pauta, id_associado)
);

INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao) VALUES
  ('Pauta1', 'descricao1', parseDateTime('20210101000000','yyyyMMddHHmmss'), parseDateTime('20210102000000','yyyyMMddHHmmss')),
  ('Pauta2', 'descricao2', null, null);


INSERT INTO VOTACAO_PAUTA (id_pauta, id_associado, voto) VALUES
('1', '1', 'SIM'),
('1', '2', 'SIM'),
('1', '3', 'SIM'),
('1', '4', 'SIM'),
('1', '5', 'SIM'),
('1', '6', 'NAO'),
('1', '7', 'SIM'),
('1', '8', 'SIM'),
('1', '9', 'SIM'),
('1', '10', 'NAO');
