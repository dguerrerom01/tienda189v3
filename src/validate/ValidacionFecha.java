package validate;

import error.EstadoError;

public class ValidacionFecha extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^(\\d{4})(\\-)(0[1-9]|1[012])(\\-)(0[1-9]|[1-2]\\d|3[01])$";

    private String value;

    public ValidacionFecha(String value){

        this.value = value;

    }
    @Override
    public int exec(){

        if(super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();

            else return EstadoError.ERROR_FECHA_BAD.getId();
    }


}

