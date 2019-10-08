package dao;


import java.io.Serializable;
import modelo.Aluguel;

/**
 *
 * @author Tiago
 */


public class AluguelDAO<TIPO> extends DAOGenerico<Aluguel> implements Serializable {
    
    public AluguelDAO(){
        super();
        classePersistente = Aluguel.class;
    }

}
