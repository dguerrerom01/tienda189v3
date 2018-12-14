package prueba;

import dao.clienteDAO.ClienteDAO;
import dao.clienteDAO.ClienteRoll;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Procedures {
    public static void main(String[] args) throws SQLException {

        ClienteRoll clienteRoll = new ClienteRoll();

       // clienteRoll.acceso.getConexion().getMetaData().getProcedureColumns("locked_client");
       //   DatabaseMetaData md = clienteRoll.acceso.getConexion().getMetaData();
       // ResultSet rs = md.getPrimaryKeys(null, getSchema(), tablename);
        DatabaseMetaData dbmd = clienteRoll.acceso.getConexion().getMetaData();
        ResultSet rs = dbmd.getProcedureColumns(null, null, "locked_client", null);
        ResultSetMetaData rsmd = rs.getMetaData();
        // Display the result set data.
        int cols = rsmd.getColumnCount();
        while(rs.next()) {
            for (int i = 1; i <= cols; i++) {
                System.out.println(rs.getString(i));
            }
        }
        rs.close();
    }
}
