package br.com.vanessa.votacao.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AssociadoRepository {

    private static Logger logger = LoggerFactory.getLogger(AssociadoRepository.class);

    @Autowired
    private RestTemplate restTemplate;
    private String associadoApiUrl = "https://user-info.herokuapp.com/users/";

    public boolean associadoPodeVotar(String cpfAssociado) {
        try {
            String url = associadoApiUrl + cpfAssociado;
            Map resposta = restTemplate.getForObject(url, Map.class);
            boolean podeVotar = resposta.get("status").toString().equalsIgnoreCase("ABLE_TO_VOTE");
            logger.info("Permissão para votação do associado [" + cpfAssociado + "]: " + podeVotar);
            return podeVotar;
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Servidor externo indisponível");
        }
    }
}
