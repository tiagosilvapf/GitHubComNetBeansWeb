package controle;

import dao.AluguelDAO;
import dao.LocatarioDAO;
import dao.UnidadeCondominialDAO;
import util.Util;
import modelo.Aluguel;
import modelo.UnidadeCondominial;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Locatario;
import modelo.Mensalidades;

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
    private LocatarioDAO<Locatario> daoLocatario;
    private Locatario locatario;
    private UnidadeCondominial unidadeCondominial;
    private Mensalidades mensalidades;
    private Boolean novaMensalidade;
    
    public ControleAluguel(){
        dao = new AluguelDAO<>();
        daoUnidadeCondominial = new UnidadeCondominialDAO<>();
        daoLocatario = new LocatarioDAO<>();
    }
    
    
    
    public String listar(){
        return "/privado/aluguel/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Aluguel();
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
   
    public void editar(Object id){
        objeto = dao.localizar(id);
    }
    
    
    
       public void remover(Object id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
       
       public void novoMensalidade(){
        setMensalidades(new Mensalidades());
        setNovaMensalidade((Boolean) true);
    }
    
    public void alterarMensalidade(int index){
        setMensalidades(objeto.getMensalidades().get(index));
        setNovaMensalidade((Boolean) false);
    }
    
    public void salvarMensalidade(){
        if (getNovaMensalidade()){
            objeto.adicionarMensalidade(getMensalidades());
        }
        Util.mensagemInformacao("Endereço adicionado com sucesso!");
    }
    
    public void removerMensalidade(int index){
        objeto.removerMensalidade(index);
        Util.mensagemInformacao("Endereço removido com sucesso!");
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

    public Mensalidades getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(Mensalidades mensalidades) {
        this.mensalidades = mensalidades;
    }

    public Boolean getNovaMensalidade() {
        return novaMensalidade;
    }

    public void setNovaMensalidade(Boolean novaMensalidade) {
        this.novaMensalidade = novaMensalidade;
    }

 

   

    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

}
