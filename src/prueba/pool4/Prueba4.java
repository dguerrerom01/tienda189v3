package prueba.pool4;

import java.sql.SQLException;
public class Prueba4 {

       public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Connection connection = null;
       // BasicConnectionPool.create(driver,url,user,password);
       // connection = BasicConnectionPool.create(driver,url,user,password).getConnection();

        ClienteMySqlConnectionPool clienteConnectionPool = new ClienteMySqlConnectionPool();
        clienteConnectionPool.useConnection(); // Se crea el pool
        System.out.println("Login operacion:" + clienteConnectionPool.update("76235216Z","Luc1234") );
        System.out.println("Login operacion:" + clienteConnectionPool.update("76235216Z","Luc2222") );
        System.out.println("Login operacion:" + clienteConnectionPool.update("76235216Z","Luc1234") );
       // ClienteH2ConnectionPool clienteH2ConnectionPool = new ClienteH2ConnectionPool();
       // clienteH2ConnectionPool.useConnection();
        //clienteH2ConnectionPool.createTables();
       // clienteH2ConnectionPool.addTables();


    }
}
