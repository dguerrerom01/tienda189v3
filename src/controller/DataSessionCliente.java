package controller;

import entity.LoginClienteEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class DataSessionCliente extends DataBundleCliente {

    private LoginClienteEntity loginClienteEntity = new LoginClienteEntity();

    public DataSessionCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        super(request);
        new CreadorDeObjetosConSessionAtributes(session,loginClienteEntity);
    }

    public LoginClienteEntity getLoginClienteEntity() {
        return loginClienteEntity;
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
