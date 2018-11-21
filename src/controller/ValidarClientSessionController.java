package controller;

import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import error.EstadoError;
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

@WebServlet("/valiCliSesion")
public class ValidarClientSessionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;

    // lista de validaciones

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        RequestDispatcher rd = request.getRequestDispatcher("cliente/clientSesion.jsp");

        DataLoginCliente dataSessionCliente = null;

        try {
            dataSessionCliente = new DataLoginCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        listaErrores.add(new ValidacionUsuario(dataSessionCliente.getUsuarioCliente()).exec());
        listaErrores.add(new ValidacionPassword(dataSessionCliente.getPasswordCliente()).exec());

        String mensaje = "";

        for(Integer error:listaErrores){
            for (EstadoError estado : EstadoError.values()){
                if(error == estado.getId() & error != 0) mensaje = mensaje.concat(estado.getMsg());
            }
        }
        request.setAttribute("mensaje", mensaje);

         if(mensaje.equals("")) {

             ClienteDAO clienteDAO = new ClienteDAO();

             String nifLogin = clienteDAO.get_nif_login(dataSessionCliente.getLoginClienteEntity());

             session.setAttribute("nifClient", nifLogin);

             if (!nifLogin.equals("null")) {

                 request.setAttribute("mensaje", ("Hola ").concat(nifLogin));

                 String opcion = request.getParameter("opcion");

                 rd = request.getRequestDispatcher("cliente/" + opcion + ".jsp");

             } else request.setAttribute("mensaje", "Cliente NO coincide login");

         }

        rd.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
