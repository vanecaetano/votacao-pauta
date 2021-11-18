package br.com.vanessa.votacao.controller;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.service.VotacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VotacaoController {

    private VotacaoService votacaoService;

    @PostMapping
    ResponseEntity create(@RequestBody @Valid Pauta pauta) {
        ResponseEntity.status(HttpStatus.CREATED)
                .body(votacaoService.criarPauta(pauta));
    }

}