package controle;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private UsuarioDAO<Usuario> dao;
    private Usuario usuarioLogado;
    private String usuario;
    private String senha;
    
    public ControleLogin(){
        dao = new UsuarioDAO<>();
    }
    
    public String irPaginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String login(){
        if(dao.login(usuario, senha)){
            usuarioLogado = dao.localizaUsuarioPorNomeUsuario(usuario);
            usuario = "";
            senha = "";
            Util.mensagemInformacao("Login efetuado com sucesso!");
            return "/index?faces-redirect=true";
        } else {
            Util.mensagemErro("Nome de usuário ou senha inválidos!");
            return "/login?faces-redirect=true";
        }
    }
    
    public String logout(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso!");
        return "/index?faces-redirect=true";
    }
    
    

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

}
