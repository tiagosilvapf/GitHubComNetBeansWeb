package dao;


import java.io.Serializable;
import modelo.Pessoa;

/**
 *
 * @author Tiago
 */


public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable {
    
    public PessoaDAO(){
        super();
        classePersistente = Pessoa.class;
    }

}
