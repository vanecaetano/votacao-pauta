package br.com.vanessa.votacao.controller;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.Voto;
import br.com.vanessa.votacao.service.PautaService;
import br.com.vanessa.votacao.service.VotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(value = "Api REST Pautas")
@CrossOrigin(origins = "*")
public class VotacaoPautaAssembleiaController {

    private static final Logger logger = LoggerFactory.getLogger(VotacaoPautaAssembleiaController.class);

    @Autowired
    private VotacaoService votacaoService;

    @Autowired
    private PautaService pautaService;

    @PostMapping("/pautas")
    @ApiOperation(value = "Cria nova pauta")
    public ResponseEntity criaPauta(@RequestBody @Valid Pauta pauta) {
        pautaService.criarPauta(pauta);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage("Pauta criada com sucesso"));
    }

    @GetMapping("/pautas")
    @ApiOperation(value = "Lista pautas cadastradas")
    public ResponseEntity listaPautas() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pautaService.buscaPautas());
    }

    @GetMapping("/pautas/{idPauta}")
    @ApiOperation(value = "Busca pauta por id")
    public ResponseEntity buscaPauta(@PathVariable Long idPauta) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pautaService.buscaPauta(idPauta));
    }

    @PostMapping("/pautas/{idPauta}/iniciar")
    @ApiOperation(value = "Abre votação em pauta")
    public ResponseEntity abreVotacao(@PathVariable Long idPauta,
                                      @RequestParam(required=true, defaultValue="1") Integer tempoEmMinutos) {
        Pauta pauta = pautaService.buscaPauta(idPauta);
        pautaService.iniciaPauta(pauta, tempoEmMinutos);
        return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("Votação iniciada"));
    }

    @RequestMapping(value = "/votacao/voto", method = RequestMethod.POST)
    @ApiOperation(value = "Recebe voto em pauta")
    public ResponseEntity recebeVoto(@RequestBody @Valid Voto voto) {
        Pauta pauta = pautaService.buscaPauta(voto.getIdPauta());
        votacaoService.recebeVoto(pauta, voto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/votacao/{idPauta}/resultado")
    @ApiOperation(value = "Retorna resultado de votação em pauta")
    public ResponseEntity resultadoVotacao(@PathVariable Long idPauta) {
        Pauta pauta = pautaService.buscaPauta(idPauta);
        return ResponseEntity.status(HttpStatus.OK)
                .body(votacaoService.resultadoVotacao(pauta));
    }

}