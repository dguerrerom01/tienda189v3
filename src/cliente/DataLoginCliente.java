package cliente;

import reflexion.RequestTransferSession;
import reflexion.SessionTransferObject;
import entity.LoginClienteHarnina;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

public  class DataLoginCliente {

    LoginClienteHarnina loginClienteEntity = new LoginClienteHarnina();
    HttpSession session;


    public DataLoginCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {

        session = request.getSession();

        new RequestTransferSession().guardarDatosSesion(request,session);

        new SessionTransferObject(session,loginClienteEntity);
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
