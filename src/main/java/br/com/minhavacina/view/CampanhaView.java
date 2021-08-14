package br.com.minhavacina.view;

import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.service.CampanhaService;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named @Data
@ViewScoped
public class CampanhaView implements Serializable {
    private CampanhaService campanhaService;
    private List<Municipio> listaDeMunicipios;
    private List<Vacina> listaDeVacinas;

    public CampanhaView() {
        this.campanhaService = new CampanhaService();
    }

    public void carregarDadosdaTela() {
        this.listaDeMunicipios = this.campanhaService.listarTodosOsMunipios().getBody();
        this.listaDeVacinas = this.campanhaService.listarTodasAsVacinas().getBody();
    }

}
