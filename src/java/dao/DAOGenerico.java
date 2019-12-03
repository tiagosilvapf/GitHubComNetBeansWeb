package dao;

import jpa.EntityManagerUtil;
import util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class DAOGenerico<TIPO> {
    
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    protected EntityManager em;
    protected Class classePersistente;
    protected String mensagem;
    protected String ordem = "id"; // qual atributo será usado para ordenar e filtrar
    protected String filtro = ""; // o valor que será aplicado no filtro
    protected Integer maximoObjetos = 3;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;
    
    public DAOGenerico(){
        em = EntityManagerUtil.getEntityManager();
    }

    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        // protegendo contral ataques de injeção de SQL
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0){
            if (ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e){
                    
                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        System.out.println("JPQL Gerada: " + jpql);
        // calculando o total de objetos da consulta
        totalObjetos = em.createQuery(jpql).getResultList().size(); 
        return em.createQuery(jpql).
                setMaxResults(maximoObjetos).
                setFirstResult(posicaoAtual).getResultList();
    }
    
    public void primeiro(){
        posicaoAtual = 0;
    }
    
    public void anterior(){
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0){
            posicaoAtual = 0;
        }
    }
    
    public void proximo(){
        if (posicaoAtual + maximoObjetos < totalObjetos){
            posicaoAtual += maximoObjetos;
        }
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0){
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }
    
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos){
            ate = totalObjetos;
        }
        return "Listagem de " + (posicaoAtual + 1) + " até " 
                + ate + " de " + totalObjetos + " registros";
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

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

}
