package dao;


import java.io.Serializable;
import modelo.Recurso;

/**
 *
 * @author Tiago
 */


public class RecursoDAO<TIPO> extends DAOGenerico<Recurso> implements Serializable {
    
    public RecursoDAO(){
        super();
        classePersistente = Recurso.class;
    }

}
