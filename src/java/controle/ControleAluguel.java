package controle;

import dao.AluguelDAO;
import dao.LocatorioDAO;
import dao.UnidadeCondominialDAO;
import util.Util;
import modelo.Aluguel;
import modelo.UnidadeCondominial;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Locatorio;

/**
 *
 * @author Tiago
 */
@ManagedBean(name = "controleAluguel")
@SessionScoped
public class ControleAluguel implements Serializable {
    
    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    private UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial;
    private LocatorioDAO<Locatorio> daoLocatorio;
    
    public ControleAluguel(){
        dao = new AluguelDAO<>();
        daoUnidadeCondominial = new UnidadeCondominialDAO<>();
    }
    
    public String listar(){
        return "/privado/aluguel/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Aluguel();
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
    
    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUnidadeCondominial() {
        return daoUnidadeCondominial;
    }

    public void setDaoUnidadeCondominial(UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial) {
        this.daoUnidadeCondominial = daoUnidadeCondominial;
    }

}
