package br.com.minhavacina.view;

import br.com.minhavacina.dto.LoginDTO;
import br.com.minhavacina.dto.TokenDTO;
import br.com.minhavacina.service.LoginService;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

import static br.com.minhavacina.util.JSFUtil.*;

@Data
@Named
@SessionScoped
public class LoginView implements Serializable {
    private final LoginService loginService;
    private final LoginDTO loginDTO;

    public LoginView() {
        this.loginService = new LoginService();
        this.loginDTO = new LoginDTO();
    }

    public String realizarLogin() {
        ResponseEntity<TokenDTO> token = this.loginService.realizarLogin(loginDTO);
        if (token.getStatusCodeValue() != 200) {
            adicionarMensagemDeErro("Dados incorretos!");
            return null;
        }
        return this.adicionarTokenNaSessao(token.getBody()) ? "index?faces-redirect=true" : null;
    }

    public boolean adicionarTokenNaSessao(TokenDTO tokenDTO) {
        if (objetoEstarNuloOuVazio(tokenDTO)) return adicionarMensagemDeErro("Ocorreu um erro");
        adicionarObjetoNaSessao("token", tokenDTO.getToken());
        return true;
    }
}
