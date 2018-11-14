package controller;

import entity.ClienteEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

public class DataInsertCliente extends DataBundleCliente {

    private ClienteEntity clienteEntity = new ClienteEntity();

    public DataInsertCliente(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        super(request);
        new CreadorDeObjetosConSessionAtributes(session,clienteEntity);
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    @Column(name = "IdCliente", nullable = false)
    @Id
    public int getIdCliente() {
        return clienteEntity.getIdCliente();
    }

    @Column(name = "NifCliente", nullable = false, length = 9)
    @Basic
    public String getNifCliente() {
        return clienteEntity.getNifCliente();
    }

    @Column(name = "ApellidosCliente", nullable = false, length = 100)
    @Basic
    public String getApellidosCliente() {
        return clienteEntity.getApellidosCliente();
    }

    @Column(name = "NombreCliente", nullable = false, length = 50)
    @Basic
    public String getNombreCliente() {
        return clienteEntity.getNombreCliente();
    }

    @Column(name = "DomicilioCliente", nullable = false, length = 100)
    @Basic
    public String getDomicilioCliente() {
        return clienteEntity.getDomicilioCliente();
    }

    @Column(name = "FechaNacimiento", nullable = false)
    @Basic
    public String getFechaNacimiento() {
        return clienteEntity.getFechaNacimiento();
    }

    @Column(name = "TelefonoCliente", nullable = false, length = 20)
    @Basic
    public String getTelefonoCliente() {
        return clienteEntity.getTelefonoCliente();
    }

    @Column(name = "MovilCliente", nullable = false, length = 20)
    @Basic
    public String getMovilCliente() {
        return clienteEntity.getMovilCliente();
    }

    @Column(name = "SexoCliente", nullable = false, length = 1)
    @Basic
    public String getSexoCliente() {
        return clienteEntity.getSexoCliente();
    }

    @Column(name = "EmailCliente", nullable = false, length = 150)
    @Basic
    public String getEmailCliente() {
        return clienteEntity.getEmailCliente();
    }

    @Column(name = "ImagenCliente", nullable = false, length = 15)
    @Basic
    public String getImagenCliente() {
        return clienteEntity.getImagenCliente();
    }

    public void setImagenCliente(String imagenCliente) {
        clienteEntity.setImagenCliente(imagenCliente);
    }

    @Column(name = "UsuarioCliente", nullable = false, length = 30)
    @Basic
    public String getUsuarioCliente() {
        return clienteEntity.getUsuarioCliente();
    }

    @Column(name = "PasswordCliente", nullable = false, length = 50)
    @Basic
    public String getPasswordCliente() {
        return clienteEntity.getPasswordCliente();
    }

    @Column(name = "fechaAlta", nullable = false)
    @Basic
    public Timestamp getFechaAlta() {
        return clienteEntity.getFechaAlta();
    }

    public String getCodigoPostalClient() {
        return clienteEntity.getCodigoPostalClient();
    }

}
