package br.com.vanessa.votacao.model;

public enum VotoOpcao {
    SIM("Sim"),
    NAO("Nao");

    private String descricao;

    VotoOpcao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}