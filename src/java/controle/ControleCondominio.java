package controle;

import dao.CondominioDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Condominio;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleCondominio")
@SessionScoped
public class ControleCondominio implements Serializable {
    
    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    
    public ControleCondominio(){
        dao = new CondominioDAO<>();
    }
    
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Condominio();
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
    
    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

}
