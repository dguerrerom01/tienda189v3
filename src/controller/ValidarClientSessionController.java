package controller;

import cliente.ComandoValidarLoginCliente;
import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import entity.DaperClienteEntity;
import error.EstadoError;
import reflexion.ObjectTransferSession;
import validate.ValidacionPassword;
import validate.ValidacionUsuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/valiCliSesion")
public class ValidarClientSessionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("cliente/clientSesion.jsp");

 //  Gestion login cliente: cargamos  variables de session y loginClienteEntity
        DataLoginCliente dataLoginCliente = null;

       try {
            dataLoginCliente = new DataLoginCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

  // Validamos con los comandos correspondientes
        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        ComandoValidarLoginCliente comandoValidarLoginCliente = new  ComandoValidarLoginCliente(dataLoginCliente.getLoginClienteEntity());

        listaErrores = comandoValidarLoginCliente.getCommands();

// Generamos los errores
       String errores = "";
        for(Integer error:listaErrores){
            for (EstadoError estado : EstadoError.values()){
                if(error == estado.getId() & error != EstadoError.ERROR_NULL.getId()) errores = errores.concat(estado.getMsg());
            }
        }
// Comprobamos si No hay errores de validación
         if(errores.equals("")) {
// Buscamos al cliente
             ClienteDAO clienteDAO = new ClienteDAO();
             String nifLogin = clienteDAO.get_nif_login(dataLoginCliente.getLoginClienteEntity());
// Comprobamos si es un cliente
             if (!nifLogin.equals("null")) {

                 session.setAttribute("nifCliente", nifLogin);

                 dataLoginCliente.setNifCliente(nifLogin);

                 request.setAttribute("mensaje", ("Saludo: Hola ").concat(nifLogin));

                 String opcion = request.getParameter("opcion");

// Si opcion es modificar sus datos personales generamos los datos para luego mostrarlo en la vista
                 if (opcion.equals("clientUpdateDaper")){

                    try {
                         new ObjectTransferSession().convertir(clienteDAO.getCliente(nifLogin), session);
                     } catch (InvocationTargetException e) {
                         e.printStackTrace();
                     } catch (IllegalAccessException e) {
                         e.printStackTrace();
                     }
                 }
// por último redirecionamos a la vista correspondiente
                 rd = request.getRequestDispatcher("cliente/" + opcion + ".jsp");

             } else // No se ha identificado correctamente
                 if(dataLoginCliente.masIntento())request.setAttribute("mensaje", "Cliente: Intentalo otra vez");
                         else {
                             session.setAttribute("horaBloqueo", new Date());
                             request.setAttribute("mensaje", "Cliente: Intentos Agotados");
                             rd = request.getRequestDispatcher("cliente/clientBlocking.jsp");
                         }

         }else  request.setAttribute("mensaje", errores);

        rd.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
