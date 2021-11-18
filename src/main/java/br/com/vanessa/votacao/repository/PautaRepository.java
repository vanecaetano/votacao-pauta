package br.com.vanessa.votacao.repository;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PautaRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int salvarPauta(Pauta pauta) {
        String sql = "INSERT INTO PAUTA (nome, descricao, data_inicio_votacao, data_fim_votacao) " +
                "VALUES (:nome, :descricao, :data_inicio_votacao, :data_fim_votacao)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("nome", pauta.getNome());
        parameters.addValue("descricao", pauta.getDescricao());
        parameters.addValue("data_inicio_votacao", pauta.getDataInicioVotacao(), Types.TIMESTAMP);
        parameters.addValue("data_fim_votacao", pauta.getDataFinalVotacao(), Types.TIMESTAMP);

        return namedParameterJdbcTemplate.update(sql, parameters);
    }

    public List<Pauta> buscaPautas() {
        String sql = "SELECT * from PAUTA";
        return namedParameterJdbcTemplate.query(sql, new PautaRowMapper());
    }

    public Optional<Pauta> buscaPauta(Long id) {
        String sql = "SELECT * from PAUTA where id =:id";

        Map<String, Long> parameters = new HashMap<String, Long>();
        parameters.put("id", id);
        return (Optional<Pauta>) Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, parameters, new PautaRowMapper()));
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

}
