package validate;

import error.EstadoError;

public class ValidacionEmail extends ValidacionRegularExpression implements IValidacion {

    private static final String patron = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";

    private String value;

    public ValidacionEmail(String value){

        this.value = value;

    }
    @Override
    public int exec(){
        if (super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();

        else return EstadoError.ERROR_EMAIL_BAD.getId();
    }
}
