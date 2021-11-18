package br.com.vanessa.votacao;

import br.com.vanessa.votacao.controller.VotacaoPautaAssembleiaController;
import br.com.vanessa.votacao.exception.*;
import br.com.vanessa.votacao.model.Pauta;
import br.com.vanessa.votacao.model.ResultadoVotacao;
import br.com.vanessa.votacao.model.Voto;
import br.com.vanessa.votacao.model.VotoOpcao;
import br.com.vanessa.votacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class VotacaoControllerTest {

	@Autowired
	VotacaoPautaAssembleiaController votacaoController;

	@MockBean
	AssociadoRepository associadoRepository;

	@Test
	@Transactional
	void criaPauta() {
		Pauta pauta = new Pauta();
		pauta.setNome("Primeira pauta");
		pauta.setDescricao("Descrição da pauta");
		ResponseEntity responseEntity = votacaoController.criaPauta(pauta);
		Assertions.assertSame(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}

	@Test
	void buscaPautas() {
		ResponseEntity<List<Pauta>> responseEntity = votacaoController.listaPautas();
		Assertions.assertSame(responseEntity.getStatusCode(), HttpStatus.OK);
		Assertions.assertEquals(2, responseEntity.getBody().size());
		List<Pauta> pautas = responseEntity.getBody();
		Assertions.assertEquals(pautas.get(0).getNome(), "Pauta1");
		Assertions.assertEquals(pautas.get(1).getNome(), "Pauta2");
	}

	@Test
	@Transactional
	void abreVotacaoPor10minutos() {
		votacaoController.abreVotacao(1l, 10);
		Pauta pauta = (Pauta) votacaoController.buscaPauta(1l).getBody();
		LocalDateTime tempDateTime = LocalDateTime.from(pauta.getDataInicioVotacao());
		long minutes = tempDateTime.until(pauta.getDataFinalVotacao(), ChronoUnit.MINUTES);
		Assertions.assertEquals(10, minutes);
	}

	@Test()
	void abrirVotacaoEmPautaInexistenteDeveLevantarExcecao() {
		PautaNaoExisteException exception = Assertions.assertThrows(
				PautaNaoExisteException.class,
				() -> {
					votacaoController.abreVotacao(5l, 1);
				}
		);
		Assertions.assertEquals("Pauta não localizada no sistema", exception.getMessage());
	}

	@Test()
	void votacaoEmPautaEncerradaDeveLevantarExcecao() {
		Voto voto = new Voto();
		voto.setIdPauta(1l);
		voto.setIdAssociado("123");
		voto.setVoto(VotoOpcao.SIM);

		VotacaoEncerradaException exception = Assertions.assertThrows(
				VotacaoEncerradaException.class,
				() -> {
					votacaoController.recebeVoto(voto);
				}
		);
		Assertions.assertEquals("Votação já foi finalizada", exception.getMessage());
	}

	@Test
	@Transactional
	void registraVoto() {
		Voto voto = new Voto();
		voto.setIdPauta(1l);
		voto.setIdAssociado("123");
		voto.setVoto(VotoOpcao.SIM);

		Mockito.when(associadoRepository.associadoPodeVotar("123")).thenReturn(true);
		votacaoController.abreVotacao(1l, 1);
		ResponseEntity responseEntity = votacaoController.recebeVoto(voto);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	@Transactional
	void votoNaoComputaSeAssociadoNaoApto() {
		Voto voto = new Voto();
		voto.setIdPauta(1l);
		voto.setIdAssociado("123");
		voto.setVoto(VotoOpcao.SIM);

		Mockito.when(associadoRepository.associadoPodeVotar("123")).thenReturn(false);
		votacaoController.abreVotacao(1l, 1);

		VotoAssociadoNaoPermitidoException exception = Assertions.assertThrows(
				VotoAssociadoNaoPermitidoException.class,
				() -> {
					votacaoController.recebeVoto(voto);
				}
		);
		Assertions.assertEquals("Associado não está habilitado para votar na pauta", exception.getMessage());
	}

	@Test
	@Transactional
	void associadoVotarNovamenteEmPautaDeveLevantarExcecao() {
		Voto voto = new Voto();
		voto.setIdPauta(1l);
		voto.setIdAssociado("123");
		voto.setVoto(VotoOpcao.SIM);

		Mockito.when(associadoRepository.associadoPodeVotar("123")).thenReturn(true);
		votacaoController.abreVotacao(1l, 1);

		votacaoController.recebeVoto(voto);

		AssociadoJaVotouPautaException exception = Assertions.assertThrows(
				AssociadoJaVotouPautaException.class,
				() -> {
					votacaoController.recebeVoto(voto);
				}
		);
		Assertions.assertEquals("Já existe registro de voto do associado na pauta", exception.getMessage());
	}

	@Test()
	void votacaoEmPautaNaoIniciadaLevantarExcecao() {
		Voto voto = new Voto();
		voto.setIdPauta(2l);
		voto.setIdAssociado("123");
		voto.setVoto(VotoOpcao.SIM);

		VotacaoNaoIniciadaException exception = Assertions.assertThrows(
				VotacaoNaoIniciadaException.class,
				() -> {
					votacaoController.recebeVoto(voto);
				}
		);
		Assertions.assertEquals("Votação ainda não foi iniciada", exception.getMessage());
	}

	@Test
	void contagemDeVotosDeveRetornarCorretamente() {
		ResultadoVotacao resultadoVotacao = (ResultadoVotacao) votacaoController.resultadoVotacao(1l).getBody();
		Assertions.assertEquals(8, resultadoVotacao.getTotalSim());
		Assertions.assertEquals(2, resultadoVotacao.getTotalNao());
	}

}
