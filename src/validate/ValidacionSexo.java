package validate;

import error.EstadoError;

public class ValidacionSexo extends  ValidacionListOfValues implements IValidacion {
    private static final String [] valores  = {"m" , "f"};

    private static final String error = "El  valor no esta en la lista";

    private String value;

    public ValidacionSexo(String value) {
        this.value = value;
    }

    @Override
    public int exec(){
        if (super.validar(value,valores)) return EstadoError.ERROR_NULL.getId();
           else return EstadoError.ERROR_NOTIN.getId();
    }



}
