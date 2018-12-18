package prueba.pool4;

import java.sql.SQLException;

public class ClienteMySqlConnectionPool {
    private  final String url = "jdbc:mysql://localhost/tienda_harnina20189vistas?useInformationSchema=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private  final String user = "root";
    private  final String password = "";

    MysqlConnectionPool mysqlConnectionPool = null;

    public void useConnection() throws SQLException, ClassNotFoundException {
        mysqlConnectionPool = new MysqlConnectionPool(url,user,password);
        mysqlConnectionPool.useConnection();
    }
    synchronized public int update(String nif, String newUser) throws SQLException {
       String sqlSentence = "UPDATE `login_cliente` SET `usuario`= '" + newUser +"'  WHERE `dni` = '" + nif + "'";
       return  mysqlConnectionPool.executeUpdate(sqlSentence);
    }
}
