package prueba.pool4;

import java.sql.SQLException;

public class H2ConnectionPool {
    private   String url = null;
    private   String user = null;
    private   String password = null;
    private  final String driver = "org.h2.Driver";
    UseConnectionPoll useConnectionPoll = new UseConnectionPoll();

    public H2ConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void useConnection() throws SQLException, ClassNotFoundException {

        useConnectionPoll.conectar(driver, url, user, password);
    }

    synchronized public int executeUpdate(String sqlSentence) throws SQLException {
        return  useConnectionPoll.executeUpdate(sqlSentence);
    }
}
