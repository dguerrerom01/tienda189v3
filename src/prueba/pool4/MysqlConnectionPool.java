package prueba.pool4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnectionPool {
    private   String url = null;
    private   String user = null;
    private   String password = null;
    private  final String driver = "com.mysql.jdbc.Driver";

    UseConnectionPoll useConnectionPoll = null;

    public MysqlConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void useConnection() throws SQLException, ClassNotFoundException {
        useConnectionPoll = new UseConnectionPoll();
        useConnectionPoll.conectar(driver, url, user, password);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return   useConnectionPoll.connection;
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException {
       return  useConnectionPoll.executeUpdate(sqlSentence);
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{

        return useConnectionPoll.executeQuery(sqlSentence);
    }

}
