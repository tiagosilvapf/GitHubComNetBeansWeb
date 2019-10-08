package controle;

import dao.MensalidadesDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Mensalidades;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleMensalidades")
@SessionScoped
public class ControleMensalidades implements Serializable {
    
    private MensalidadesDAO<Mensalidades> dao;
    private Mensalidades objeto;
    
    public ControleMensalidades(){
        dao = new MensalidadesDAO<>();
    }
    
    public String listar(){
        return "/privado/mensalidades/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Mensalidades();
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
    
    public MensalidadesDAO<Mensalidades> getDao() {
        return dao;
    }

    public void setDao(MensalidadesDAO<Mensalidades> dao) {
        this.dao = dao;
    }

    public Mensalidades getObjeto() {
        return objeto;
    }

    public void setObjeto(Mensalidades objeto) {
        this.objeto = objeto;
    }

}
