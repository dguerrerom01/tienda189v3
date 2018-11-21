package validate;

import java.util.ArrayList;
import java.util.List;

public class ValidacionMultiValidation {

    private String error;

    ArrayList<Integer> listaErrores = new ArrayList<Integer>();

    protected ArrayList<Integer> validar(IValidacion validadores[]) {

        for (int i = 0; i < validadores.length; i++) {

            listaErrores.add(validadores[i].exec());

        }
        return listaErrores;
    }
    public static ArrayList<Integer> validar(List<IValidacion> validador ) {

        ArrayList<Integer> listaErro = new ArrayList<Integer>();

        for(IValidacion vali:validador){

            listaErro.add(vali.exec());

        }
        return listaErro;
    }
    /*
    public static String validar(List<IValidacion> validador ) {

        for(IValidacion vali:validador){

            if (!vali.validar()) {
                return vali.getError();
            }
        }
        return "";
    }
*/

}




