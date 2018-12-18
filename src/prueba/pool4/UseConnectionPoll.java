package prueba.pool4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UseConnectionPoll {

    private  String url = null;
    private  String user = null;
    private  String password = null;
    private  String driver = null;
    Connection connection = null;

    synchronized public void conectar(String driver,String url, String user,String password ) throws SQLException, ClassNotFoundException {

       connection = BasicConnectionPool.create(driver,url,user,password).getConnection();
    }

    synchronized  public Connection getConnection() throws SQLException, ClassNotFoundException {
        return   connection;
    }
    synchronized public int executeUpdate(String sqlSentence) throws SQLException {
        if(connection == null) return -1;
        Statement sentence = (Statement) connection.createStatement();
        return sentence.executeUpdate(sqlSentence);
    }

    synchronized public ResultSet executeQuery(String sqlSentence) throws SQLException{
        if(connection == null) return null;
        Statement sentence = (Statement) connection.createStatement();
        return sentence.executeQuery(sqlSentence);
    }

    synchronized public void iniciarTransaccion() throws SQLException{
        connection.setAutoCommit(false);
    }

    synchronized public void aceptarTransaccion() throws SQLException{
        connection.commit();
        connection.setAutoCommit(true);
 }

    synchronized public void cancelarTransaccion() throws SQLException{
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
