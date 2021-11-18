package br.com.vanessa.votacao.exception;

public class VotoAssociadoNaoPermitidoException extends RuntimeException {

    private String message;

    public VotoAssociadoNaoPermitidoException(String message) {
        super(message);
        this.message = message;
    }

    public VotoAssociadoNaoPermitidoException() {
    }
}
