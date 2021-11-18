DROP TABLE IF EXISTS PAUTA;

CREATE TABLE PAUTA (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  descricao VARCHAR(250) NOT NULL,
  data_inicio_votacao TIMESTAMP NULL,
  data_fim_votacao TIMESTAMP NULL
);

create table VOTACAO_PAUTA (
 id_pauta  integer not null,
 id_associado varchar(250) not null,
 voto varchar(3) not null,
 primary key(id_pauta, id_associado)
);

INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao) VALUES
  ('Pauta1', 'descricao1', parseDateTime('20210101000000','yyyyMMddHHmmss'), parseDateTime('20210102000000','yyyyMMddHHmmss')),
  ('Pauta2', 'descricao2', parseDateTime('20210101000000','yyyyMMddHHmmss'), parseDateTime('20210102000000','yyyyMMddHHmmss'));

