package br.com.vanessa.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotacaoEncerradaException extends RuntimeException {

    private String message;

    public VotacaoEncerradaException(String message) {
        super(message);
        this.message = message;
    }

    public VotacaoEncerradaException() {
    }
}
