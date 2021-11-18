package br.com.vanessa.votacao.exception;

public class VotacaoEncerradaException extends RuntimeException {

    private String message;

    public VotacaoEncerradaException(String message) {
        super(message);
        this.message = message;
    }

    public VotacaoEncerradaException() {
    }
}
