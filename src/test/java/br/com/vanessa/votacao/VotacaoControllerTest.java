package br.com.vanessa.votacao;

import br.com.vanessa.votacao.controller.VotacaoController;
import br.com.vanessa.votacao.model.Pauta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class VotacaoControllerTest {

	@Autowired
	VotacaoController votacaoController;

	@Test
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
		Assertions.assertTrue(responseEntity.getBody().size() == 2);
		List<Pauta> pautas = responseEntity.getBody();
		Assertions.assertEquals(pautas.get(0).getNome(), "Pauta1");
		Assertions.assertEquals(pautas.get(1).getNome(), "Pauta2");
	}

}
