package controle;

import dao.LocatarioDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Locatario;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleLocatorio")
@SessionScoped
public class ControleLocatario implements Serializable {
    
    private LocatarioDAO<Locatario> dao;
    private Locatario objeto;
    
    public ControleLocatario(){
        dao = new LocatarioDAO<>();
    }
    
    public String listar(){
        return "/privado/locatorio/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Locatario();
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
    
    public LocatarioDAO<Locatario> getDao() {
        return dao;
    }

    public void setDao(LocatarioDAO<Locatario> dao) {
        this.dao = dao;
    }

    public Locatario getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }

}
