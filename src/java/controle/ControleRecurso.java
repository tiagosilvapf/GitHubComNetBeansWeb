package controle;

import dao.RecursoDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Recurso;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleRecurso")
@SessionScoped
public class ControleRecurso implements Serializable {
    
    private RecursoDAO<Recurso> dao;
    private Recurso objeto;
    
    public ControleRecurso(){
        dao = new RecursoDAO<>();
    }
    
    public String listar(){
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Recurso();
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
    
    public RecursoDAO<Recurso> getDao() {
        return dao;
    }

    public void setDao(RecursoDAO<Recurso> dao) {
        this.dao = dao;
    }

    public Recurso getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

}
