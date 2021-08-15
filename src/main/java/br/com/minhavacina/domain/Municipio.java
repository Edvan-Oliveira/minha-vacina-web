package br.com.minhavacina.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Municipio {
    private Integer id;
    private String nome;
    private List<Campanha> campanhas;

    public Municipio() {
        this.campanhas = new ArrayList<>();
    }
}
