package br.com.vanessa.votacao.repository;

import br.com.vanessa.votacao.model.Voto;
import br.com.vanessa.votacao.model.VotoOpcao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VotoRowMapper implements RowMapper<Voto> {

    @Override
    public Voto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Voto voto = new Voto();
        voto.setIdPauta(rs.getLong("id_pauta"));
        voto.setIdAssociado(rs.getString("id_associado"));
        voto.setVoto(VotoOpcao.valueOf(rs.getString("voto")));
        return voto;
    }
}