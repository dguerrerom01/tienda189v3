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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/valiCliSesion")
public class ValidarClientSessionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        LoginClienteEntity cliente = new LoginClienteEntity();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        String error = "";

        List<IValidacion> validador = new ArrayList<IValidacion>();

        RequestDispatcher rd = request.getRequestDispatcher("clientSession.jsp");
        cliente.setUsuarioCliente(request.getParameter("clientUsuario"));

        validador.add(new ValidacionUsuario(cliente.getUsuarioCliente()));
        error += ValidacionMultiValidation.validar(validador);
        if (!(error.length() > 0)) {
            validador.clear();

            validador.add(new ValidacionPassword(cliente.getPasswordCliente()));
            error += ValidacionMultiValidation.validar(validador);
        }
        if (error.length() > 0){
            request.setAttribute("error", error);
        }else {

            request.setAttribute("error", "Validado ok");

            ClienteDAO clienteDAO = new ClienteDAO();

            String login = clienteDAO.get_nif_login(cliente);
            System.out.println("login:" + login);

            if (login != "null"){

                session.setAttribute("nif", login);
                login = ("Hola ").concat(login);
                request.setAttribute("mensaje", login);

            }
            else request.setAttribute("mensaje", "Cliente NO login");

            rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");

            rd.forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
