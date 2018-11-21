package validate;

import error.EstadoError;

public class ValidacionLetrasConEspacio extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^([a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*)$";

    private String value;

    public ValidacionLetrasConEspacio(String value) {
        this.value = value;
    }

    @Override
    public int exec(){

        if(super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();
            else return EstadoError.ERROR_LETTER_SPACE.getId();
    }

}

