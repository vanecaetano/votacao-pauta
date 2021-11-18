package br.com.vanessa.votacao.model;

import java.time.LocalDateTime;
import java.util.List;

public class PautaSessao {
    private LocalDateTime dataExpiracao;
    private List<Voto> votos;
}
