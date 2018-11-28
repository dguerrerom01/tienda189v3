package validate;

import dao.clienteDAO.ClienteRoll;
import dao.cp.CPDAO;
import error.EstadoError;

import java.sql.SQLException;

public class VerificacionCodigoPostal implements  IValidacion {

    private String value;

    public VerificacionCodigoPostal(String value) {
        this.value = value;
    }

    @Override
    public int exec() {

        ClienteRoll clienteRoll = new ClienteRoll();

        CPDAO cpdao = new CPDAO(clienteRoll.getUsuario(), clienteRoll.getPass());

        try {
            if (!cpdao.check_cp(value)) {

                return EstadoError.ERROR_CODIGOPOSTAL_INCORRECTO.getId();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return EstadoError.ERROR_NULL.getId();
    }
}
