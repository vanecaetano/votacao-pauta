package br.com.vanessa.votacao.controller;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VotacaoController {

    @Autowired
    private VotacaoService votacaoService;

    @PostMapping
    public ResponseEntity criaPauta(@RequestBody @Valid Pauta pauta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(votacaoService.criarPauta(pauta));
    }

    @GetMapping
    public ResponseEntity listaPautas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(votacaoService.buscaPautas());
    }

}