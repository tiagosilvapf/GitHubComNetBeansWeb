package dao;


import java.io.Serializable;
import modelo.Locatorio;

/**
 *
 * @author Tiago
 */


public class LocatorioDAO<TIPO> extends DAOGenerico<Locatorio> implements Serializable {
    
    public LocatorioDAO(){
        super();
        classePersistente = Locatorio.class;
    }
    

}
