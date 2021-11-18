package br.com.vanessa.votacao.service;

import br.com.vanessa.votacao.model.Pauta;
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
        pauta.setDataFinalVotacao(LocalDateTime.now().plusMinutes(tempoEmMinutos));
        return votacaoRepository.atualizaPauta(pauta);
    }
}
