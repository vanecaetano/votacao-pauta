package br.com.vanessa.votacao.model;

import java.time.LocalDateTime;

public class Pauta {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicioVotacao;
    private LocalDateTime dataFinalVotacao;
    private PautaSituacao situacao = PautaSituacao.FECHADA;

}
