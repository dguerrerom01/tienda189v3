package controller;

import entity.LoginClienteEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

public abstract class DataBundleCliente {

    LoginClienteEntity loginClienteEntity = new LoginClienteEntity();

    HttpSession session;

    public DataBundleCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {

        session = request.getSession();

        new GuardadorDeRequesParamsEnSession().guardarDatosSesion(request,session);

        new CreadorDeObjetosConSessionAtributes(session,loginClienteEntity);
    }

}
