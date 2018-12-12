package cliente;

import entity.DaperClienteEntity;
import error.Error;
import validate.*;

import java.util.ArrayList;
import java.util.Objects;

public class ComandoValidarDaperCliente implements ComandoValidarCliente {

    DaperClienteEntity daperClienteEntity = null;

    public ComandoValidarDaperCliente(DaperClienteEntity daperClienteEntity){
        this.daperClienteEntity = daperClienteEntity;
    }

    @Override
    public ArrayList<Error> getCommands() {
        ArrayList<Error> errors = new ArrayList<>();

        errors.add(new ValidacionDNINIECIF(daperClienteEntity.getNifCliente()).exec());
        errors.add(new ValidacionLetrasConEspacio(daperClienteEntity.getNombreCliente()).exec());
        errors.add(new ValidacionLongitud(daperClienteEntity.getNombreCliente(), 3, 50).exec());
        errors.add(new ValidacionLetrasConEspacio(daperClienteEntity.getApellidosCliente()).exec());
        errors.add(new ValidacionLongitud(daperClienteEntity.getApellidosCliente(), 15, 100).exec());
        errors.add(new ValidacionCodigoPostal(daperClienteEntity.getCodigoPostalCliente()).exec());
        errors.add(new ValidarDomicilio(daperClienteEntity.getDomicilioCliente()).exec());
        errors.add(new ValidacionLongitud(daperClienteEntity.getDomicilioCliente(), 2, 100).exec());
        errors.add(new ValidacionTelefonoSpain(daperClienteEntity.getTelefonoCliente()).exec());
        errors.add(new ValidacionTelefonoSpain(daperClienteEntity.getMovilCliente()).exec());
        errors.add(new ValidacionFecha(daperClienteEntity.getFechaNacimiento().toString()).exec());
        errors.add(new ValidacionSexo(daperClienteEntity.getSexoCliente()).exec());
        errors.add(new ValidacionEmail(daperClienteEntity.getEmailCliente()).exec());
        errors.add(new VerificacionCodigoPostal(daperClienteEntity.getCodigoPostalCliente()).exec());
        errors.removeIf(Objects::isNull);

        return errors;
    }
}
