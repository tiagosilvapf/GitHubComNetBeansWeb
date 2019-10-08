package controle;

import dao.LocatorioDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Locatorio;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleLocatorio")
@SessionScoped
public class ControleLocatorio implements Serializable {
    
    private LocatorioDAO<Locatorio> dao;
    private Locatorio objeto;
    
    public ControleLocatorio(){
        dao = new LocatorioDAO<>();
    }
    
    public String listar(){
        return "/privado/locatorio/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Locatorio();
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
    
    public LocatorioDAO<Locatorio> getDao() {
        return dao;
    }

    public void setDao(LocatorioDAO<Locatorio> dao) {
        this.dao = dao;
    }

    public Locatorio getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatorio objeto) {
        this.objeto = objeto;
    }

}
