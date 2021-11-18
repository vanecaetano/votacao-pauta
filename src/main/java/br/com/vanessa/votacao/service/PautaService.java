package br.com.vanessa.votacao.service;

import br.com.vanessa.votacao.exception.PautaNaoExisteException;
import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public void criarPauta(Pauta pauta) {
        pautaRepository.salvarPauta(pauta);
    }

    public List<Pauta> buscaPautas() {
        return pautaRepository.buscaPautas();
    }

    public Pauta buscaPauta(Long idPauta) {
        Optional<Pauta> pauta = pautaRepository.buscaPauta(idPauta);
        if (!pauta.isPresent()) throw new PautaNaoExisteException("Pauta não localizada no sistema");
        return pauta.get();
    }

    public int iniciaPauta(Pauta pauta, Integer tempoEmMinutos) {
        pauta.setDataInicioVotacao(LocalDateTime.now());
        pauta.setDataFinalVotacao(pauta.getDataInicioVotacao().plusMinutes(tempoEmMinutos));
        return pautaRepository.atualizaPauta(pauta);
    }
}
