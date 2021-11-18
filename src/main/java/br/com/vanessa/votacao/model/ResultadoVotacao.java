package br.com.vanessa.votacao.model;

import java.util.Objects;

public class ResultadoVotacao {

    private Long idPauta;
    private long totalSim;
    private long totalNao;
    private boolean votacaoEncerrada;

    public ResultadoVotacao(Long idPauta, long totalSim, long totalNao, boolean votacaoEncerrada) {
        this.idPauta = idPauta;
        this.totalSim = totalSim;
        this.totalNao = totalNao;
        this.votacaoEncerrada = votacaoEncerrada;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public long getTotalSim() {
        return totalSim;
    }

    public void setTotalSim(long totalSim) {
        this.totalSim = totalSim;
    }

    public long getTotalNao() {
        return totalNao;
    }

    public void setTotalNao(long totalNao) {
        this.totalNao = totalNao;
    }

    public boolean isVotacaoEncerrada() {
        return votacaoEncerrada;
    }

    public void setVotacaoEncerrada(boolean votacaoEncerrada) {
        this.votacaoEncerrada = votacaoEncerrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoVotacao that = (ResultadoVotacao) o;
        return totalSim == that.totalSim && totalNao == that.totalNao && votacaoEncerrada == that.votacaoEncerrada && Objects.equals(idPauta, that.idPauta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPauta, totalSim, totalNao, votacaoEncerrada);
    }
}
