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

        loginClienteEntity.setUsuarioCliente(usuarioCliente);
    }

    public void setNifCliente(String nifCliente) {

        loginClienteEntity.setNifCliente(nifCliente);
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

   // Nuevo para contador de intentos

    private void setSessionAttribute(String s, Object o) {
        session.setAttribute(s, o);
    }

    private void reiniciarIntentos() {

        this.setIntento(0);
    }

    private void setIntento(int intento) {

        this.setSessionAttribute("intento", intento);
    }

    private int getIntento() {

        return (Integer) session.getAttribute("intento");
    }

    private void incrementarIntento() {
        int intento = this.getIntento();
        intento++;
        this.setIntento(intento);
    }

    private int getMaxIntento() {

        return (Integer) session.getAttribute("maxIntento");
    }

    public boolean  masIntento(){
        incrementarIntento();
        if(getIntento() >= getMaxIntento()) return false;
        return true;
    }

}
