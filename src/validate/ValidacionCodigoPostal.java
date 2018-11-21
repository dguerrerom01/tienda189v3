package validate;

import error.EstadoError;

public class ValidacionCodigoPostal  extends ValidacionRegularExpression implements IValidacion {

    private static final String patron ="^(?:0[1-9][0-9]{3}|[1-4][0-9]{4}|5[0-2][0-9]{3})$";

    private String value;

    public ValidacionCodigoPostal(String value){

        this.value = value;

    }
    @Override
    public int exec(){
        if(super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();
            else return EstadoError.ERROR_CODIGOPOSTAL_INCORRECTO.getId();
    }


}
