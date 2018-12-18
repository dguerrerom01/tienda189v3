package prueba.pool3;

import java.sql.SQLException;

public class Probamos {
    public static void main(String[] args) throws SQLException {
        ConnectionPool connectionPool = BasicConnectionPool
                .create("com.mysql.jdbc.Driver", "root", "");
    }
}
