package br.com.vanessa.votacao.repository;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class VotacaoRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void salvarPauta(Pauta pauta) {
        String sql = "INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao) " +
                "VALUES (:nome, :descricao, :data_inicio_votacao, :data_fim_votacao)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("nome", pauta.getNome());
        parameters.addValue("descricao", pauta.getDescricao());
        parameters.addValue("data_inicio_votacao", pauta.getDataInicioVotacao(), Types.TIMESTAMP);
        parameters.addValue("data_fim_votacao", pauta.getDataFinalVotacao(), Types.TIMESTAMP);

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    public List<Pauta> buscaPautas() {
        String sql = "SELECT * from PAUTA";
        return namedParameterJdbcTemplate.query(sql, new PautaRowMapper());
    }

    public Optional<Pauta> buscaPauta(Long id) {
        String sql = "SELECT * from PAUTA where id =:id";

        Map<String, Long> parameters = new HashMap<String, Long>();
        parameters.put("id", id);
        return (Optional<Pauta>) Optional.of(namedParameterJdbcTemplate.queryForObject(sql, parameters, new PautaRowMapper()));
    }

    public boolean existeVotoAssociado(Long idPauta, String idAssociado) {
        String sql = "SELECT * from VOTACAO_PAUTA where id_pauta=:id_pauta AND id_associado=:id_associado";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id_pauta", idPauta);
        parameters.put("id_associado", idAssociado);
        try {
            namedParameterJdbcTemplate.queryForObject(sql, parameters, new VotoRowMapper());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public int atualizaPauta(Pauta pauta) {
        String sql = "UPDATE PAUTA SET data_inicio_votacao=:data_inicio_votacao, " +
                "data_fim_votacao=:data_fim_votacao " +
                "WHERE id =:id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("data_inicio_votacao", pauta.getDataInicioVotacao(), Types.TIMESTAMP);
        parameters.addValue("data_fim_votacao", pauta.getDataFinalVotacao(), Types.TIMESTAMP);
        parameters.addValue("id", pauta.getId());

        return namedParameterJdbcTemplate.update(sql, parameters);
    }

    public void registraVoto(Voto voto) {
        String sql = "INSERT INTO VOTACAO_PAUTA (id_pauta, id_associado, voto) " +
                "VALUES (:id_pauta, :id_associado, :voto)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id_pauta", voto.getIdPauta());
        parameters.addValue("id_associado", voto.getIdAssociado());
        parameters.addValue("voto", voto.getVoto().getDescricao());
        namedParameterJdbcTemplate.update(sql, parameters);
    }

    public List<Voto> buscaVotos(Long idPauta) {
        String sql = "SELECT * from VOTACAO_PAUTA where id_pauta=:id_pauta";
        Map<String, Long> parameters = new HashMap<String, Long>();
        parameters.put("id_pauta", idPauta);
        return namedParameterJdbcTemplate.query(sql, parameters, new VotoRowMapper());
    }
}
