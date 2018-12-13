package dao.clienteDAO;

import cliente.DataPersonCliente;
import entity.ClienteEntity;
import entity.DaperClienteEntity;
import entity.LoginClienteHarnina;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ClienteDAO {

    ClienteRoll clienteRoll = new ClienteRoll();

  public int add_cliente(ClienteEntity cliente) {
       String sql = "INSERT INTO `cliente`(`NifCliente`, `ApellidosCliente`, `NombreCliente`, `CodigoPostalCliente`, `DomicilioCliente`, `FechaNacimiento`, `TelefonoCliente`, `MovilCliente`, `SexoCliente`, `EmailCliente`, `ImagenCliente`, `UsuarioCliente`, `PasswordCliente`) " + " VALUES ('" + cliente.getNifCliente() + "','" +
               cliente.getApellidosCliente()+ "','" +
               cliente.getNombreCliente()+ "','" +
               cliente.getCodigoPostalCliente()+ "','" +
               cliente.getDomicilioCliente()+ "','" +
               cliente.getFechaNacimiento()+ "','" +
               cliente.getTelefonoCliente()+ "','" +
               cliente.getMovilCliente()+ "','" +
               cliente.getSexoCliente()+ "','" +
               cliente.getEmailCliente()+ "','" +
               cliente.getImagenCliente()+ "','" +
               cliente.getUsuarioCliente()+ "','" +
               cliente.getPasswordCliente()+ "')";

       System.out.println(sql);
        try {

            return clienteRoll.insertUpdateDelete(sql);

        } catch (SQLException e) {

            return 0;
        }
    }

  public boolean add_cliente_procedure (DataPersonCliente dataPersonCliente) {
        try {
            return clienteRoll.add_cliente(dataPersonCliente);
        } catch (SQLException e) {
            System.out.println("DAO false");
            return false;
        }
    }

  public Boolean deleteClient(String nif) throws SQLException {
        return clienteRoll.deleteClient(nif);
    }

  public String getEmailClient(String nif) throws SQLException {
      return clienteRoll.getEmailClient(nif);
  }

  public String getClaveBloqueo(String nif) throws SQLException {
        return clienteRoll.getClaveBloqueo(nif);
    }

  public ArrayList getListaClientes(){
        return clienteRoll.getListaClientes();
    }

  public String  get_nif_login(LoginClienteHarnina clientloginEntity) {

       try
       {
           return clienteRoll.get_nif_login(clientloginEntity.getUsuarioCliente(),clientloginEntity.getPasswordCliente());
       }
       catch (Exception ignore)
       {
           // do something appropriate with the exception, *at least*:
           //e.printStackTrace();
       }
       return "null";
    }

  public DaperClienteEntity getCliente(String nif){
        return (DaperClienteEntity) clienteRoll.getCliente(nif);
    }

  public boolean locked_client(String nif)throws SQLException {
        String uuid = UUID.randomUUID().toString();
        String clave = uuid.substring(0, Math.min(uuid.length(), 50));
      return clienteRoll.lockedClient(nif, clave);
    }

  public boolean update_client_login(LoginClienteHarnina cliente) throws SQLException {
      return clienteRoll.update_client_login(cliente);
  }

  public boolean update_client_daper(DaperClienteEntity cliente, String usuario) throws SQLException {

      return clienteRoll.update_client_daper(cliente,usuario);
    }



}
