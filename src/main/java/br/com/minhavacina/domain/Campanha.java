package br.com.minhavacina.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Campanha {
    private Integer id;
    private String nome;
    private String descricao;
    private Vacina vacina;
    private Municipio municipio;
    private boolean ativa;
    private Date dataInicio;
    private Date dataFim;
    private Integer idadeMinima;
    private Integer idadeMaxima;

    public Campanha() {
        this.vacina = new Vacina();
        this.municipio = new Municipio();
    }
}
