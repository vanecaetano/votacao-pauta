package br.com.vanessa.votacao.repository;

import br.com.vanessa.votacao.model.Pauta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PautaRowMapper implements RowMapper<Pauta> {

    @Override
    public Pauta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pauta pauta = new Pauta();
        pauta.setId(rs.getLong("id"));
        pauta.setNome(rs.getString("nome"));
        pauta.setDescricao(rs.getString("descricao"));
        if (rs.getTimestamp("data_inicio_votacao") != null) pauta.setDataInicioVotacao(rs.getTimestamp("data_inicio_votacao").toLocalDateTime());
        if (rs.getTimestamp("data_fim_votacao") != null) pauta.setDataFinalVotacao(rs.getTimestamp("data_fim_votacao").toLocalDateTime());
        return pauta;
    }
}