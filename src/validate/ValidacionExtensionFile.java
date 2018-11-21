package validate;

import error.EstadoError;

public class ValidacionExtensionFile extends ValidacionListOfValues implements IValidacion {


    private String value;
    private String [] lista_extension;

    public ValidacionExtensionFile(String value,String [] listaExtension) {
        this.value = value;
        this.lista_extension =  listaExtension;
    }

    @Override
    public int exec() {

        if(super.validar(value, lista_extension)) return EstadoError.ERROR_NULL.getId();
            else return EstadoError.ERROR_EXTENSION_BAD.getId();

    }


}
