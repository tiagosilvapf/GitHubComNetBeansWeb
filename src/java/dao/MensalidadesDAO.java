package dao;


import java.io.Serializable;
import modelo.Mensalidades;

/**
 *
 * @author Tiago
 */


public class MensalidadesDAO<TIPO> extends DAOGenerico<Mensalidades> implements Serializable {
    
    public MensalidadesDAO(){
        super();
        classePersistente = Mensalidades.class;
    }

}
