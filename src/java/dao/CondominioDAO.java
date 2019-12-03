package dao;


import java.io.Serializable;
import modelo.Condominio;

/**
 *
 * @author Tiago
 */


public class CondominioDAO<TIPO> extends DAOGenerico<Condominio> implements Serializable {
    
    public CondominioDAO(){
        super();
        classePersistente = Condominio.class;
    }

}


