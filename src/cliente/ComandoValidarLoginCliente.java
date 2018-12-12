package cliente;

import entity.LoginClienteHarnina;
import error.Error;
import validate.ValidacionPassword;
import validate.ValidacionUsuario;

import java.util.ArrayList;
import java.util.Objects;

public class ComandoValidarLoginCliente implements ComandoValidarCliente {

    LoginClienteHarnina loginClienteHarnina = null;

   public ComandoValidarLoginCliente(LoginClienteHarnina loginClienteHarnina){
      this.loginClienteHarnina = loginClienteHarnina;
   }
    public  ArrayList<Error> getCommands(){

        ArrayList<Error> errors = new ArrayList<>();

        errors.add(new ValidacionUsuario(loginClienteHarnina.getUsuarioCliente()).exec());
        errors.add(new ValidacionPassword(loginClienteHarnina.getPasswordCliente()).exec());
        errors.removeIf(Objects::isNull);

        return errors;

    }

}
