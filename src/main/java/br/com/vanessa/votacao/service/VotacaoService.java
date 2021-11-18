package br.com.vanessa.votacao.service;

import br.com.vanessa.votacao.exception.PautaNaoExisteException;
import br.com.vanessa.votacao.exception.VotacaoEncerradaException;
import br.com.vanessa.votacao.exception.VotacaoNaoIniciadaException;
import br.com.vanessa.votacao.exception.VotoAssociadoNaoPermitidoException;
import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.ResultadoVotacao;
import br.com.vanessa.votacao.model.Voto;
import br.com.vanessa.votacao.model.VotoOpcao;
import br.com.vanessa.votacao.repository.AssociadoRepository;
import br.com.vanessa.votacao.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotacaoService {

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    public void recebeVoto(Pauta pauta, Voto voto) {
        if (pauta.getDataInicioVotacao() == null) throw new VotacaoNaoIniciadaException("Votação ainda não foi iniciada");
        if (LocalDateTime.now().isAfter(pauta.getDataFinalVotacao())) throw new VotacaoEncerradaException("Pauta já foi finalizada");
        if (associadoRepository.associadoPodeVotar(voto.getIdAssociado())) votacaoRepository.registraVoto(voto);
        else throw new VotoAssociadoNaoPermitidoException("Associado não está habilitado para votar na pauta");
    }

    public ResultadoVotacao resultadoVotacao(Pauta pauta) {
        if (pauta.getDataInicioVotacao() == null) throw new VotacaoNaoIniciadaException("Votação ainda não foi iniciada");
        List<Voto> votos = votacaoRepository.buscaVotos(pauta.getId());
        long totalVotosSim = votos.stream().filter( voto -> voto.getVoto() == VotoOpcao.SIM ).count();
        long totalVotosNao = votos.stream().filter( voto -> voto.getVoto() == VotoOpcao.NAO ).count();
        boolean votacaoEncerrada = LocalDateTime.now().isAfter(pauta.getDataFinalVotacao());
        return new ResultadoVotacao(pauta.getId(), totalVotosSim, totalVotosNao, votacaoEncerrada);
    }
}
