package br.com.vanessa.votacao.repository;

import br.com.vanessa.votacao.model.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VotacaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int salvarPauta(Pauta pauta) {
        return jdbcTemplate.update("INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao) VALUES(?,?,?,?)",
                pauta.getNome(), pauta.getDescricao(), pauta.getDataInicioVotacao(), pauta.getDataFinalVotacao());
    }

    public List<Pauta> buscaPautas() {
        return jdbcTemplate.query("SELECT * from PAUTA", BeanPropertyRowMapper.newInstance(Pauta.class));
    }

    public Pauta buscaPauta(Long id) {
        try {
            Pauta pauta = jdbcTemplate.queryForObject("SELECT * FROM PAUTA WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Pauta.class), id);

            return pauta;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int atualizaPauta(Pauta pauta) {
        return jdbcTemplate.update("UPDATE PAUTA SET data_inicio_votacao=?, data_fim_votacao=? WHERE id=?",
                pauta.getDataInicioVotacao(), pauta.getDataFinalVotacao(), pauta.getId());
    }
}
