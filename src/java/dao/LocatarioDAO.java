package dao;


import java.io.Serializable;
import modelo.Locatario;

/**
 *
 * @author Tiago
 */


public class LocatarioDAO<TIPO> extends DAOGenerico<Locatario> implements Serializable {
    
    public LocatarioDAO(){
        super();
        classePersistente = Locatario.class;
    }
    

}
