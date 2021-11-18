package br.com.vanessa.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotoAssociadoNaoPermitidoException extends RuntimeException {

    private String message;

    public VotoAssociadoNaoPermitidoException(String message) {
        super(message);
        this.message = message;
    }

    public VotoAssociadoNaoPermitidoException() {
    }
}
