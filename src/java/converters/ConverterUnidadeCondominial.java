package converters;

import jpa.EntityManagerUtil;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.UnidadeCondominial;

/**
 *
 * @author Tiago
 */

@FacesConverter(value = "converterUnidadeCondominial")
public class ConverterUnidadeCondominial implements Serializable, Converter{

    // converte da tela para o controle
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return EntityManagerUtil.getEntityManager().find(UnidadeCondominial.class, Integer.parseInt(string));
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        UnidadeCondominial obj = (UnidadeCondominial) o;
        return obj.getId().toString();
    }

}
