package br.com.minhavacina.domain;

import lombok.Data;

import java.util.List;

@Data
public class Local {
    private Integer id;
    private String descricao;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private Municipio municipio;
    private List<Campanha> campanhas;
}
