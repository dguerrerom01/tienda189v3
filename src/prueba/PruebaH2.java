package prueba;

import dao.poolConexion.ClienteH2ConnectionPool;

import java.sql.SQLException;

public class PruebaH2 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
         ClienteH2ConnectionPool clienteH2ConnectionPool = new ClienteH2ConnectionPool(3,5);
         clienteH2ConnectionPool.useConnection();
         //clienteH2ConnectionPool.createTables();
         clienteH2ConnectionPool.addTables();

    }
}
