package br.com.minhavacina;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.domain.Municipio;

public class Executavel {
    public static void main(String[] args) {
        ClienteRest clienteRest = new ClienteRest();
        Municipio municipio = clienteRest.chamarMetodoGet("http://localhost:8080/minha-vacina-api/municipios/5",
                Municipio.class);

        System.out.println(municipio);
    }
}
