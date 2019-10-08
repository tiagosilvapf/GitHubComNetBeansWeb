package controle;

import dao.UnidadeCondominialDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.UnidadeCondominial;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleUnidadeCondominial")
@SessionScoped
public class ControleUnidadeCondominial implements Serializable {
    
    private UnidadeCondominialDAO<UnidadeCondominial> dao;
    private UnidadeCondominial objeto;
    
    public ControleUnidadeCondominial(){
        dao = new UnidadeCondominialDAO<>();
    }
    
    public String listar(){
        return "/privado/unidade_condominial/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new UnidadeCondominial();
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
    
    public UnidadeCondominialDAO<UnidadeCondominial> getDao() {
        return dao;
    }

    public void setDao(UnidadeCondominialDAO<UnidadeCondominial> dao) {
        this.dao = dao;
    }

    public UnidadeCondominial getObjeto() {
        return objeto;
    }

    public void setObjeto(UnidadeCondominial objeto) {
        this.objeto = objeto;
    }

}
