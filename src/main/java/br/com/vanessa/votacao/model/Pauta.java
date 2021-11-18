package br.com.vanessa.votacao.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Pauta {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicioVotacao;
    private LocalDateTime dataFinalVotacao;
    private PautaSituacao situacao = PautaSituacao.FECHADA;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicioVotacao() {
        return dataInicioVotacao;
    }

    public void setDataInicioVotacao(LocalDateTime dataInicioVotacao) {
        this.dataInicioVotacao = dataInicioVotacao;
    }

    public LocalDateTime getDataFinalVotacao() {
        return dataFinalVotacao;
    }

    public void setDataFinalVotacao(LocalDateTime dataFinalVotacao) {
        this.dataFinalVotacao = dataFinalVotacao;
    }

    public PautaSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(PautaSituacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id) && Objects.equals(nome, pauta.nome) && Objects.equals(descricao, pauta.descricao) && Objects.equals(dataInicioVotacao, pauta.dataInicioVotacao) && Objects.equals(dataFinalVotacao, pauta.dataFinalVotacao) && situacao == pauta.situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, dataInicioVotacao, dataFinalVotacao, situacao);
    }
}
