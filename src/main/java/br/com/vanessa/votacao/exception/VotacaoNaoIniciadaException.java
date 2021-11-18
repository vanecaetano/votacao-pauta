package br.com.vanessa.votacao.exception;

public class VotacaoNaoIniciadaException extends RuntimeException {

    private String message;

    public VotacaoNaoIniciadaException(String message) {
        super(message);
        this.message = message;
    }

    public VotacaoNaoIniciadaException() {
    }
}
