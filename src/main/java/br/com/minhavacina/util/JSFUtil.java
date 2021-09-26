package br.com.minhavacina.util;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

    public static void adicionarMensagemDeSucesso(String titulo) {
        adicionarMensagemDeSucesso(titulo, null);
    }

    public static void adicionarMensagemDeSucesso(String titulo, String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, msg);
    }

    public static boolean adicionarMensagemDeErro(String titulo) {
        return adicionarMensagemDeErro(titulo, null);
    }

    public static boolean adicionarMensagemDeErro(String titulo, String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, msg);
        return false;
    }

    public static void adicionarMensagemDeAviso(String titulo) {
        adicionarMensagemDeAviso(titulo, null);
    }

    public static void adicionarMensagemDeAviso(String titulo, String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensagem);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, msg);
    }

    public static void abrirDialogo(String nome) {
        PrimeFaces.current().executeScript("PF('" + nome + "').show()");
    }

    public static void fecharDialogo(String nome) {
        PrimeFaces.current().executeScript("PF('" + nome + "').hide()");
    }

    public static void adicionarObjetoNaSessao(String nome, Object objeto) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nome, objeto);
    }

    public static Boolean removerObjetoDaSessao(String nome) {
        Boolean remocaoConcluida = false;
        if (objetoExisteNaSessaoIhNaoEstaNulo(nome)) {
            Object objeto = resgatarObjetoDaSessao(nome);
            remocaoConcluida = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(nome, objeto);
        }
        return remocaoConcluida;
    }

    public static Boolean objetoExisteNaSessaoIhNaoEstaNulo(String nome) {
        if (resgatarObjetoDaSessao(nome) != null) return true;
        else return false;
    }

    public static Object resgatarObjetoDaSessao(String nome) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nome);
    }

    public static Boolean objetoEstarNuloOuVazio(Object objeto) {
        if (objeto == null) return true;
        if (objeto.equals("")) return true;
        return false;
    }

    public static Boolean objetoNaoEstarNuloNemVazio(Object objeto) {
        if (objeto != null) {
            if (!objeto.equals("")) return true;
        }
        return false;
    }

}
