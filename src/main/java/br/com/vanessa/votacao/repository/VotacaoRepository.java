package br.com.vanessa.votacao.repository;

import br.com.vanessa.votacao.model.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VotacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int salvarPauta(Pauta pauta) {
        return jdbcTemplate.update("INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao, situacao) VALUES(?,?,?,?,?)",
                pauta.getNome(), pauta.getDescricao(), pauta.getDataInicioVotacao(), pauta.getDataFinalVotacao(), pauta.getSituacao().getDescricao());
    }

    public List<Pauta> buscaPautas() {
        return jdbcTemplate.query("SELECT * from PAUTA", BeanPropertyRowMapper.newInstance(Pauta.class));
    }
}
