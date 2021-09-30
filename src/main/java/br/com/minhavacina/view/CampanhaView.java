package br.com.minhavacina.view;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.service.CampanhaService;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.minhavacina.util.JSFUtil.*;

@Data
@Named
@ViewScoped
public class CampanhaView implements Serializable {
    private CampanhaService campanhaService;
    private List<Municipio> listaDeMunicipios;
    private List<Vacina> listaDeVacinas;
    private Campanha campanha;
    private List<Campanha> listaDeCampanhas;
    private boolean statusListaCampanhas;

    public CampanhaView() {
        this.campanhaService = new CampanhaService();
        this.campanha = new Campanha();
        this.listaDeCampanhas = new ArrayList<>();
        this.statusListaCampanhas = true;
    }

    public void carregarDadosdaTela() {
        this.listaDeMunicipios = this.campanhaService.listarTodosOsMunipios().getBody();
        this.listaDeVacinas = this.campanhaService.listarTodasAsVacinas().getBody();
        this.listaDeCampanhas = this.campanhaService.listarCampanhasAtivas().getBody();
    }

    public void cadastrarCampanha() {
        int codigoDoStatus = this.campanhaService.cadastrarCampanha(this.campanha).getStatusCodeValue();
        if (codigoDoStatus == 201) adicionarMensagemDeSucesso("Campanha cadastrada com sucesso!");
        else adicionarMensagemDeErro("Ocorreu um erro!");
        this.limparObjetos();
        fecharDialogo("dlgCadastroCampanha");
    }

    public void limparObjetos() {
        this.campanha = new Campanha();
        this.listaDeCampanhas = this.campanhaService.listarCampanhasAtivas().getBody();
    }

    public String formatarDataParaVisualizacao(String data) {
        String[] s = data.split("T");
        String dataFormatada = "----";
        try {
            if (objetoNaoEstarNuloNemVazio(data)) {
                Date dataUtil = new SimpleDateFormat("yyyy-MM-dd").parse(s[0]);
                dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataUtil);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataFormatada;
    }

    public String formatarIdadeParaVisualizacao(String idade) {
        return objetoNaoEstarNuloNemVazio(idade) ? idade + " anos" : "----";
    }

    public void eventoAjaxListarCampanhasAtivasOuInativas() {
        ResponseEntity<List<Campanha>> campanhasResponseEntity;

        if (statusListaCampanhas) campanhasResponseEntity = this.campanhaService.listarCampanhasAtivas();
        else campanhasResponseEntity = this.campanhaService.listarCampanhasInativas();

        this.listaDeCampanhas = validarRespostaListaCampanhas(campanhasResponseEntity)
                ? campanhasResponseEntity.getBody() : new ArrayList<>();
    }

    private boolean validarRespostaListaCampanhas(ResponseEntity<List<Campanha>> campanhasResponseEntity) {
        if (campanhasResponseEntity.getStatusCodeValue() != 200
                || objetoEstarNuloOuVazio(campanhasResponseEntity.getBody())) {
            return adicionarMensagemDeErro("Ocorreu um erro");
        }
        return true;
    }
}
