package br.com.vanessa.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VotacaoNaoIniciadaException extends RuntimeException {

    private String message;

    public VotacaoNaoIniciadaException(String message) {
        super(message);
        this.message = message;
    }

    public VotacaoNaoIniciadaException() {
    }
}
