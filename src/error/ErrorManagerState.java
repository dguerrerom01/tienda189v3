package error;

import cliente.DataLoginCliente;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ErrorManagerState {

    protected EstadoError estadoError;

    void setEstado(EstadoError estadoError) {
        this.estadoError = estadoError;
    }

    public abstract void consecuencia(DataLoginCliente dataBundleCliente, HttpServletResponse response) throws IOException;
}
