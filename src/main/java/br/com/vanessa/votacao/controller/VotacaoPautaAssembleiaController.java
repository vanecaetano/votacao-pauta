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
        } catch (PautaNaoExisteException pne) {
            logger.error(ERRO_ABRIR_VOTACAO, pne);
            return new ResponseEntity(new ResponseMessage(pne.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            logger.error(ERRO_ABRIR_VOTACAO, e);
            return new ResponseEntity(new ResponseMessage(ERRO_ABRIR_VOTACAO), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/votacao/voto", method = RequestMethod.POST)
    public ResponseEntity recebeVoto(@RequestBody @Valid Voto voto) {
            Pauta pauta = pautaService.buscaPauta(voto.getIdPauta());
            votacaoService.recebeVoto(pauta, voto);
            return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/votacao/{idPauta}/resultado")
    public ResponseEntity resultadoVotacao(@PathVariable Long idPauta) {
        try {
            Pauta pauta = pautaService.buscaPauta(idPauta);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(votacaoService.resultadoVotacao(pauta));
        } catch (RuntimeException e) {
            logger.error("Erro ao buscar resultado da votação", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private static final String ERRO_ABRIR_VOTACAO = "Erro ao abrir votação de pauta";
    private static final String ERRO_REGISTRAR_VOTO = "Erro ao registrar voto";

}