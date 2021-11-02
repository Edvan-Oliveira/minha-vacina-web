package br.com.minhavacina.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
public class Local {
    @EqualsAndHashCode.Exclude
    private Integer id;
    private String descricao;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private Municipio municipio;
    @EqualsAndHashCode.Exclude
    private List<Campanha> campanhas;

    public Local() {
        this.municipio = new Municipio();
        this.campanhas = new ArrayList<>();
    }
}
