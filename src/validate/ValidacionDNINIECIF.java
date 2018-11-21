package validate;

import error.EstadoError;

/**
 * Clase que calcula o valida un documento de identificación del reino de España. (DNI,NIE,CIF).
 * @author yo
 * El antecedente del NIF es el CIF, utilizado en personas jurídicas
 */
public class ValidacionDNINIECIF implements IValidacion{

    private String documento;

    private static final  String mensajeError = "Documento Incorrecto";


    public ValidacionDNINIECIF(String documento){
        this.documento = documento;
    }

    @Override
    public int exec() {
        ValidacionDNINIF validacionDNINIF = new ValidacionDNINIF(documento);
        if (validacionDNINIF.exec()!= 0){
            ValidacionNIE validacionNIE = new ValidacionNIE(documento);
            if(validacionNIE.exec() != 0){
                ValidacionNIFCIF validacionNIFCIF = new ValidacionNIFCIF(documento);
                if(validacionNIFCIF.exec() != 0){
                    return EstadoError.ERROR_NIF_8DIGIT_LETTER.getId();
                }
            }
        }
        return EstadoError.ERROR_NULL.getId();
    }



}
