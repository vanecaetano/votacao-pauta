DROP TABLE IF EXISTS PAUTA;

CREATE TABLE PAUTA (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  descricao VARCHAR(250) NOT NULL,
  data_inicio_votacao DATE NULL,
  data_fim_votacao DATE NULL
);

INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao) VALUES
  ('Pauta1', 'descricao1', parseDateTime('20210101000000','yyyyMMddHHmmss'), parseDateTime('20210102000000','yyyyMMddHHmmss')),
  ('Pauta2', 'descricao2', parseDateTime('20210101000000','yyyyMMddHHmmss'), parseDateTime('20210102000000','yyyyMMddHHmmss'));

