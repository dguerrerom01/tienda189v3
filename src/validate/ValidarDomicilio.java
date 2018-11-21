package validate;

import error.EstadoError;

public class ValidarDomicilio extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^([0-9ºª.:,/a-zA-ZñÑáéíóúÜüÁÉÍÓÚ\\s]*)$";

    private String value;

    public ValidarDomicilio(String value){
        this.value = value;
    }

    @Override
    public int exec() {

        if(super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();
           else return EstadoError.ERROR_DOMICILIO_BAD.getId();
    }


}
