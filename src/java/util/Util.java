package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author Tiago
 */


public class Util {
    
    public static String getMensagemErro(Exception e){
        while (e.getCause() != null){
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if (retorno.contains("viola restrição de chave estrangeira")){
            retorno = "Registro não pode ser excluído pois outros objetos no sistema "
                    + " estão o utilizando";
        }
        return retorno;
    }
    
    public static void mensagemInformacao(String msg){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        contexto.addMessage(null, mensagem);
    }
       
    public static void mensagemErro(String msg){
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        contexto.addMessage(null, mensagem);
    }
        

}
