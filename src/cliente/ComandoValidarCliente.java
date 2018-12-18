package cliente;

import error.Error;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ComandoValidarCliente {
    ArrayList<Error> getCommands() throws SQLException, ClassNotFoundException;
}
