package br.com.minhavacina.service;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.clientrest.TipoAutenticacao;
import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.minhavacina.shared.Constantes.*;
import static br.com.minhavacina.util.JSFUtil.resgatarObjetoDaSessao;

public class CampanhaService {
    private ClienteRest clienteRest;

    public CampanhaService() {
        this.clienteRest = new ClienteRest();
        this.recuperarTokenDaSessao();
    }

    private void recuperarTokenDaSessao() {
        String token = (String) resgatarObjetoDaSessao("token");
        this.clienteRest.setTipoAutenticacao(TipoAutenticacao.BEARER_TOKEN);
        this.clienteRest.setToken(token);
    }

    public ResponseEntity<List<Municipio>> listarTodosOsMunipios() {
        return this.clienteRest.chamarMetodoGetListagem(MUNICIPIO);
    }

    public ResponseEntity<List<Vacina>> listarTodasAsVacinas() {
        return this.clienteRest.chamarMetodoGetListagem(VACINA);
    }

    public ResponseEntity<Campanha> cadastrarCampanha(Campanha campanha) {
        return this.clienteRest.chamarMetodoPost(CAMPANHA, campanha, Campanha.class);
    }

    public ResponseEntity<List<Campanha>> listarCampanhasAtivas() {
        return this.clienteRest.chamarMetodoGetListagem(CAMPANHA);
    }

    public ResponseEntity<List<Campanha>> listarCampanhasInativas() {
        return this.clienteRest.chamarMetodoGetListagem(CAMPANHA_INATIVA, Campanha[].class);
    }

}
