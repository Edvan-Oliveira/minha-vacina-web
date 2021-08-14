package br.com.minhavacina.service;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.shared.Constantes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CampanhaService {
    private ClienteRest clienteRest;

    public CampanhaService() {
        this.clienteRest = new ClienteRest();
    }

    public ResponseEntity<List<Municipio>> listarTodosOsMunipios() {
        return this.clienteRest.chamarMetodoGetListagem(Constantes.MUNICIPIO);
    }

    public ResponseEntity<List<Vacina>> listarTodasAsVacinas() {
        return this.clienteRest.chamarMetodoGetListagem(Constantes.VACINA);
    }

    public ResponseEntity<Campanha> cadastrarCampanha(Campanha campanha) {
        return this.clienteRest.chamarMetodoPost(Constantes.CAMPANHA, campanha, Campanha.class);
    }
}
