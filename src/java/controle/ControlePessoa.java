package controle;

import dao.PessoaDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pessoa;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {
    
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;
    
    public ControlePessoa(){
        dao = new PessoaDAO<>();
    }
    
    public String listar(){
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Pessoa();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Object id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Object id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

}
