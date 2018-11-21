package validate;

import error.EstadoError;

public class ValidacionLetrasSinEspacio extends ValidacionRegularExpression implements IValidacion {

    private String patron =  "^([a-zA-ZñÑáéíóúÁÉÍÓÚ\\S]*)$";

    private String value;

    public ValidacionLetrasSinEspacio(String value) {
        this.value = value;
    }

    @Override
    public int exec() {

        if(super.validar(value, patron)) return EstadoError.ERROR_NULL.getId();
            else return EstadoError.ERROR_LETTER_ONLY.getId();
    }



}