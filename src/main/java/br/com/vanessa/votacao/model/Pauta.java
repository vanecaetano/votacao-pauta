package br.com.vanessa.votacao.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Pauta implements Serializable {

    private Long id;
    @NotBlank(message = "campo [nome] é obrigatório")
    private String nome;
    @NotBlank(message = "Campo [descricao] é obrigatório")
    private String descricao;
    private LocalDateTime dataInicioVotacao;
    private LocalDateTime dataFinalVotacao;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id) && Objects.equals(nome, pauta.nome) && Objects.equals(descricao, pauta.descricao) && Objects.equals(dataInicioVotacao, pauta.dataInicioVotacao) && Objects.equals(dataFinalVotacao, pauta.dataFinalVotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, dataInicioVotacao, dataFinalVotacao);
    }
}
