package validate;

import error.EstadoError;

public class ValidacionTelefonoSpain extends ValidacionRegularExpression implements IValidacion {
    private static final String patron = "^(\\+34|0034|34)?[6789]\\d{8}$";//"^(\+34|0034|34)?[6789]\d{8}$";

    private static final String mensajeError = "Telefono Español Incorrecto";

    private String value;
    public ValidacionTelefonoSpain(String value){

        this.value = value;

    }
    @Override
    public int exec(){
        if( super.validar(value,patron)) return EstadoError.ERROR_NULL.getId();
          else return EstadoError.ERROR_PHONE_BAD.getId();
    }


}
