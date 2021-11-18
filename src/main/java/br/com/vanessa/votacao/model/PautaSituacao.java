package br.com.vanessa.votacao.model;

public enum PautaSituacao {
    ABERTA("aberta"),
    FECHADA ("fechada");

    private String descricao;

    PautaSituacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
