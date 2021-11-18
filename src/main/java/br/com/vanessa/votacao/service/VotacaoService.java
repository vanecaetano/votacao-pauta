package br.com.vanessa.votacao.service;

import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.ResultadoVotacao;
import br.com.vanessa.votacao.model.Voto;
import br.com.vanessa.votacao.model.VotoOpcao;
import br.com.vanessa.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotacaoService {

    @Autowired
    private VotacaoRepository votacaoRepository;

    public int criarPauta(Pauta pauta) {
        return votacaoRepository.salvarPauta(pauta);
    }

    public List<Pauta> buscaPautas() {
        return votacaoRepository.buscaPautas();
    }

    public int abreVotacao(Long idPauta, Integer tempoEmMinutos) {
        Pauta pauta = votacaoRepository.buscaPauta(idPauta);
        //verificar se pauta já não foi iniciada etc
        pauta.setDataInicioVotacao(LocalDateTime.now());
        pauta.setDataFinalVotacao(pauta.getDataInicioVotacao().plusMinutes(tempoEmMinutos));
        return votacaoRepository.atualizaPauta(pauta);
    }

    public int recebeVoto(Voto voto) {
        Pauta pauta = votacaoRepository.buscaPauta(voto.getIdPauta());
        if (LocalDateTime.now().isAfter(pauta.getDataFinalVotacao())) throw new RuntimeException("Pauta finalizada");
        // se pauta não existe, envia erro not found
        // verifica se usuario pode votar
        votacaoRepository.registraVoto(voto);
        return 1;
    }

    public ResultadoVotacao resultadoVotacao(Long idPauta) {
        Pauta pauta = votacaoRepository.buscaPauta(idPauta);
        //se pauta não existir, levantar exceção
        List<Voto> votos = votacaoRepository.buscaVotos(idPauta);
        ResultadoVotacao resultadoVotacao = new ResultadoVotacao();
        resultadoVotacao.setIdPauta(idPauta);
        long totalVotosSim = votos.stream().filter( voto -> voto.getVoto() == VotoOpcao.SIM ).count();
        long totalVotosNao = votos.stream().filter( voto -> voto.getVoto() == VotoOpcao.NAO ).count();
        resultadoVotacao.setTotalNao(totalVotosNao);
        resultadoVotacao.setTotalSim(totalVotosSim);
        resultadoVotacao.setVotacaoEncerrada(LocalDateTime.now().isAfter(pauta.getDataFinalVotacao()));
        return resultadoVotacao;
    }
}
