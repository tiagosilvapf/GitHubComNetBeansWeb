package controle;

import dao.CondominioDAO;
import dao.PessoaDAO;
import util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import modelo.Condominio;
import modelo.Pessoa;
import modelo.UnidadeCondominial;

/**
 *
 * @author Tiago
 */

@ManagedBean(name = "controleCondominio")
@SessionScoped
@ViewScoped
public class ControleCondominio implements Serializable {
    
    private CondominioDAO<Condominio> dao;
    private PessoaDAO<Pessoa> daoPessoa;
    private Condominio objeto;
    private UnidadeCondominial unidadeCondominial;
    private Boolean novaUnidade;
    
    public ControleCondominio(){
        dao = new CondominioDAO<>();
         daoPessoa = new PessoaDAO<>();
    }
    
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Condominio();
   
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
    
     public void novaUnidade(){
        setUnidadeCondominial(new UnidadeCondominial());
        setNovaUnidade((Boolean) true);
    }
     

    
    public void alterarUnidade(int index){
        setUnidadeCondominial(objeto.getUnidade().get(index));
        setNovaUnidade((Boolean) false);
    }
    
    public void salvarUnidade(){
        if (getNovaUnidade()){
            objeto.adicionarUnidade(getUnidadeCondominial());
        }
        Util.mensagemInformacao("Unidade condominial adicionada com sucesso!");
    }
    
    public void removerUnidade(int index){
        objeto.removerUnidade(index);
        Util.mensagemInformacao("Unidade condominial removida com sucesso!");
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

    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    public void setUnidadeCondominial(UnidadeCondominial UnidadeCondominial) {
        this.unidadeCondominial = UnidadeCondominial;
    }

    public Boolean getNovaUnidade() {
        return novaUnidade;
    }

    public void setNovaUnidade(Boolean novaUnidade) {
        this.novaUnidade = novaUnidade;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }
    

}
