package br.com.vanessa.votacao.controller;

import br.com.vanessa.votacao.exception.PautaNaoExisteException;
import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.Voto;
import br.com.vanessa.votacao.service.PautaService;
import br.com.vanessa.votacao.service.VotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VotacaoPautaAssembleiaController {

    private static final Logger logger = LoggerFactory.getLogger(VotacaoPautaAssembleiaController.class);

    @Autowired
    private VotacaoService votacaoService;

    @Autowired
    private PautaService pautaService;

    @PostMapping("/pautas")
    public ResponseEntity criaPauta(@RequestBody @Valid Pauta pauta) {
        pautaService.criarPauta(pauta);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage("Pauta criada com sucesso"));
    }

    @GetMapping("/pautas")
    public ResponseEntity listaPautas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pautaService.buscaPautas());
    }

    @PostMapping("/pautas/{idPauta}/iniciar")
    public ResponseEntity abreVotacao(@PathVariable Long idPauta,
                                      @RequestParam(required=true, defaultValue="1") Integer tempoEmMinutos) {
        try {
            Pauta pauta = pautaService.buscaPauta(idPauta);
            pautaService.iniciaPauta(pauta, tempoEmMinutos);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("Votação iniciada"));
        } catch (PautaNaoExisteException pautaNaoExisteException) {
            logger.error("Erro ao abrir votação de pauta", pautaNaoExisteException);
            return new ResponseEntity(new ResponseMessage(pautaNaoExisteException.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/votacao/voto")
    public ResponseEntity recebeVoto(@RequestBody @Valid Voto voto) {
        try {
            Pauta pauta = pautaService.buscaPauta(voto.getIdPauta());
            votacaoService.recebeVoto(pauta, voto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (PautaNaoExisteException pautaNaoExisteException) {
            logger.error("Erro ao registrar voto", pautaNaoExisteException);
            return new ResponseEntity(pautaNaoExisteException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/votacao/{idPauta}/resultado")
    public ResponseEntity resultadoVotacao(@PathVariable Long idPauta) {
        try {
            Pauta pauta = pautaService.buscaPauta(idPauta);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(votacaoService.resultadoVotacao(pauta));
        } catch (PautaNaoExisteException pautaNaoExisteException) {
            logger.error("Erro ao buscar resultado da votação", pautaNaoExisteException);
            return new ResponseEntity(pautaNaoExisteException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}