package br.com.minhavacina.service;

import br.com.minhavacina.clientrest.ClienteRest;
import br.com.minhavacina.dto.LoginDTO;
import br.com.minhavacina.dto.TokenDTO;
import br.com.minhavacina.shared.Constantes;
import org.springframework.http.ResponseEntity;

public class LoginService {
    private ClienteRest clienteRest;

    public LoginService() {
        this.clienteRest = new ClienteRest();
    }

    public ResponseEntity<TokenDTO> realizarLogin(LoginDTO loginDTO) {
        return this.clienteRest.chamarMetodoPost(Constantes.LOGIN, loginDTO, TokenDTO.class);
    }
}
