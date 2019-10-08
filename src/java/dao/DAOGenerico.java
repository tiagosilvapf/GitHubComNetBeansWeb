package dao;

import jpa.EntityManagerUtil;
import util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Tiago
 */


public class DAOGenerico<TIPO> {
    
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    protected EntityManager em;
    protected Class classePersistente;
    protected String mensagem;
    
    public DAOGenerico(){
        em = EntityManagerUtil.getEntityManager();
    }

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }    
    
    public boolean persist(TIPO obj){
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e){
            rollback();
            mensagem = "Erro ao persistir objeto: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean merge(TIPO obj){
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e){
            rollback();
            mensagem = "Erro ao persistir objeto: " + Util.getMensagemErro(e);
            return false;
        }
    }   
    
    public boolean remove(TIPO obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e){
            rollback();
            mensagem = "Erro ao remover objeto: " + Util.getMensagemErro(e);
            return false;
        }
    }   
    
    public TIPO localizar(Object id){
        rollback();
        TIPO obj = (TIPO) em.find(classePersistente, id);
        return obj;
    }
    
    public void rollback(){
        if (em.getTransaction().isActive() == false){
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
    }
    

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }



    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassePersistente() {
        return classePersistente;
    }

    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
