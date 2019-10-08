package dao;


import java.io.Serializable;
import modelo.UnidadeCondominial;

/**
 *
 * @author Tiago
 */


public class UnidadeCondominialDAO<TIPO> extends DAOGenerico<UnidadeCondominial> implements Serializable {
    
    public UnidadeCondominialDAO(){
        super();
        classePersistente = UnidadeCondominial.class;
    }

}
