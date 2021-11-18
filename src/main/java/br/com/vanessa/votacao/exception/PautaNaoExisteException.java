package br.com.vanessa.votacao.exception;

public class PautaNaoExisteException extends RuntimeException {

    private String message;

    public PautaNaoExisteException(String message) {
        super(message);
        this.message = message;
    }

    public PautaNaoExisteException() {
    }
}
