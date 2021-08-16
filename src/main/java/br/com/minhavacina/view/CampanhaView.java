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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named @Data
@ViewScoped
public class CampanhaView implements Serializable {
    private CampanhaService campanhaService;
    private List<Municipio> listaDeMunicipios;
    private List<Vacina> listaDeVacinas;
    private Campanha campanha;
    private List<Campanha> listaDeCampanhas;

    public CampanhaView() {
        this.campanhaService = new CampanhaService();
        this.campanha = new Campanha();
        this.listaDeCampanhas = new ArrayList<>();
    }

    public void carregarDadosdaTela() {
        this.listaDeMunicipios = this.campanhaService.listarTodosOsMunipios().getBody();
        this.listaDeVacinas = this.campanhaService.listarTodasAsVacinas().getBody();
        this.listaDeCampanhas = this.campanhaService.listarTodasAsCampanhas().getBody();
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

    public String formatarDataParaVisualizacao(String data) {
        String[] s = data.split("T");
        String dataFormatada = "----    ";
        try {
            if (JSFUtil.objetoNaoEstarNuloNemVazio(data)) {
                Date dataUtil = new SimpleDateFormat("yyyy-MM-dd").parse(s[0]);
                dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataUtil);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataFormatada;
    }

    public String formatarIdadeParaVisualizacao(String idade) {
        return JSFUtil.objetoNaoEstarNuloNemVazio(idade) ? idade + " anos" : "----";
    }

}
