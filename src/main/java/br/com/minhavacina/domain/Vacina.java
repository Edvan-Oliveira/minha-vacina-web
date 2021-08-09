package br.com.minhavacina.domain;

import lombok.Data;

@Data
public class Vacina {
    private Integer id;
    private String nome;
    private String descricao;
    private String caminhoImagem;
}
