package controller;

import dao.clienteDAO.ClienteDAO;
import entity.LoginClienteEntity;
import validate.IValidacion;
import validate.ValidacionMultiValidation;
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
import java.util.List;

@WebServlet("/valiCliSesion")
public class ValidarClientSessionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        RequestDispatcher rd;

        DataSessionCliente dataSessionCliente = null;

        try {
            dataSessionCliente = new DataSessionCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ValidacionUsuario validacionUsuario = new ValidacionUsuario(dataSessionCliente.getUsuarioCliente());
        if(validacionUsuario.validar()) {

            ValidacionPassword validacionPassword = new ValidacionPassword(dataSessionCliente.getPasswordCliente());

            if(validacionPassword.validar()){

                //verificamos si está en BD
                ClienteDAO clienteDAO = new ClienteDAO();

                String login = clienteDAO.get_nif_login(dataSessionCliente.getLoginClienteEntity());

                if (!login.equals("null")){

                    request.setAttribute("mensaje", ("Hola ").concat(login));

                    rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");

                    rd.forward(request, response);

                }else request.setAttribute("mensaje", "Cliente NO coincide login");

            }else request.setAttribute("mensaje", validacionPassword.getError());

        } else request.setAttribute("mensaje", validacionUsuario.getError());

        rd = request.getRequestDispatcher("cliente/clientSesion.jsp");

        rd.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
