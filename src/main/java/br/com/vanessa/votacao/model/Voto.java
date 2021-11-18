package br.com.vanessa.votacao.model;

import java.util.Objects;

public class Voto {
    private Long idPauta;
    private String idAssociado;
    private VotoOpcao voto;

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public String getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(String idAssociado) {
        this.idAssociado = idAssociado;
    }

    public VotoOpcao getVoto() {
        return voto;
    }

    public void setVoto(VotoOpcao voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto1 = (Voto) o;
        return idPauta.equals(voto1.idPauta) && idAssociado.equals(voto1.idAssociado) && voto == voto1.voto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPauta, idAssociado, voto);
    }
}
