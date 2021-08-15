package br.com.minhavacina.view;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.service.CampanhaService;
import br.com.minhavacina.util.JSFUtil;
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
    private Campanha campanha;

    public CampanhaView() {
        this.campanhaService = new CampanhaService();
    }

    public void carregarDadosdaTela() {
        this.listaDeMunicipios = this.campanhaService.listarTodosOsMunipios().getBody();
        this.listaDeVacinas = this.campanhaService.listarTodasAsVacinas().getBody();
        this.campanha = new Campanha();
    }

    public void cadastrarCampanha() {
        int codigoDoStatus = this.campanhaService.cadastrarCampanha(this.campanha).getStatusCodeValue();
        if (codigoDoStatus == 201) JSFUtil.adicionarMensagemDeSucesso("Campanha cadastrada com sucesso!");
        else JSFUtil.adicionarMensagemDeErro("Ocorreu um erro!");
        this.limparCampanha();
        JSFUtil.fecharDialogo("dlgCadastroCampanha");
    }

    public void limparCampanha() {
        this.campanha = new Campanha();
    }

}
