package prueba;

import dao.clienteDAO.ClienteDAO;
import entity.LoginClienteHarnina;

import java.sql.SQLException;

public class Rollpruebapool {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClienteDAO clienteDAO = new ClienteDAO();
        LoginClienteHarnina loginClienteHarnina = new LoginClienteHarnina();
        loginClienteHarnina.setNifCliente("76235216Z");
        loginClienteHarnina.setUsuarioCliente("Luc222");
        loginClienteHarnina.setPasswordCliente("Luc_12345");
        System.out.println(clienteDAO.update_client_login(loginClienteHarnina));
    }
}
