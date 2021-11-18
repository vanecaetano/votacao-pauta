package br.com.vanessa.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PautaNaoExisteException.class)
    public final ResponseEntity<ErrorResponse> handlePautaInexistente(PautaNaoExisteException ex, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VotacaoEncerradaException.class)
    public final ResponseEntity<ErrorResponse> handleVotacaoEncerrada(VotacaoEncerradaException ex, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VotacaoNaoIniciadaException.class)
    public final ResponseEntity<ErrorResponse> handleVotacaoNaoIniciada(VotacaoNaoIniciadaException ex, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VotoAssociadoNaoPermitidoException.class)
    public final ResponseEntity<ErrorResponse> handleAssociadoSemPermissao(VotoAssociadoNaoPermitidoException ex, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AssociadoJaVotouPautaException.class)
    public final ResponseEntity<ErrorResponse> handleVotoJaComputado(AssociadoJaVotouPautaException ex, WebRequest request)
    {
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
