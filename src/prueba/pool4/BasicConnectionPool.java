package prueba.pool4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {

    private final String url;
    private final String user;
    private final String password;
    private final String driver;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 10;
    private final int MAX_POOL_SIZE = 20;

    public static BasicConnectionPool create(String driver, String url, String user, String password) throws SQLException, ClassNotFoundException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        Class.forName(driver);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
            System.out.println("Conectado a BD " + url);
        }

        return new BasicConnectionPool(driver,url, user, password, pool);
    }

    private BasicConnectionPool(String driver,String url, String user, String password, List<Connection> connectionPool) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {

        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection(url, user, password));
            } else {
                throw new RuntimeException("Se alcanzó el tamaño máximo del pool, no hay conexiones disponibles!");
            }
        }
        //Elimina el elemento en la posición especificada en esta lista (operación opcional).
        // Desplaza cualquier elemento posterior a la izquierda (resta uno de sus índices).
        // Devuelve el elemento que fue eliminado de la lista.
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection( String url, String user, String password) throws SQLException, ClassNotFoundException {

        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public List<Connection> getConnectionPool() {
        return connectionPool;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }


}