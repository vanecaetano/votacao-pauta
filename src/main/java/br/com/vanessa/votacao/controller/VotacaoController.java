package br.com.vanessa.votacao.controller;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VotacaoController {

    @Autowired
    private VotacaoService votacaoService;

    @PostMapping("/pautas")
    public ResponseEntity criaPauta(@RequestBody @Valid Pauta pauta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(votacaoService.criarPauta(pauta));
    }

    @GetMapping("/pautas")
    public ResponseEntity listaPautas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(votacaoService.buscaPautas());
    }

    @PostMapping("/votacao/{idPauta}/iniciar")
    public ResponseEntity abreVotacao(@PathVariable Long idPauta,
                                      @RequestParam(required=true, defaultValue="1") Integer tempoEmMinutos) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(votacaoService.abreVotacao(idPauta, tempoEmMinutos));
    }

    //votar


}