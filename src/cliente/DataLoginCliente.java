package cliente;

import utils.POJOCreatorSession;
import entity.LoginClienteHarnina;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class DataLoginCliente extends DataBundleCliente {


    public DataLoginCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        super(request);

    }

    public LoginClienteHarnina getLoginClienteEntity() {
        return loginClienteEntity;
    }

    public void setUsuarioCliente(String usuarioCliente) {

        loginClienteEntity.setNifCliente(usuarioCliente);
    }

    public String getNifCliente() {
        return loginClienteEntity.getNifCliente();
    }

    public String getUsuarioCliente() {
        return loginClienteEntity.getUsuarioCliente();
    }

    public String getPasswordCliente() {
        return loginClienteEntity.getPasswordCliente();
    }


}
