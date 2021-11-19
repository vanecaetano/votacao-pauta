CREATE TABLE PAUTA (
  id serial PRIMARY KEY,
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