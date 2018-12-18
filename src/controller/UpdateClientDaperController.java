package controller;


import cliente.ComandoValidarDaperCliente;
import cliente.DataPersonCliente;
import dao.clienteDAO.ClienteDAO;
import error.Error;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/valiCliUpdateDaper")
public class UpdateClientDaperController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        DataPersonCliente dataPersonCliente = null;

        try {
            dataPersonCliente = new DataPersonCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ComandoValidarDaperCliente  comandosDaper = new ComandoValidarDaperCliente(dataPersonCliente.getDaperClienteEntity());
        ArrayList<Error> errors = null;
        try {
            errors = comandosDaper.getCommands();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(errors.isEmpty()) {

            ClienteDAO clienteDAO = null;
            try {
                clienteDAO = new ClienteDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                if(clienteDAO.update_client_daper(dataPersonCliente.getDaperClienteEntity(), (String) session.getAttribute("usuarioCliente"))) request.setAttribute("mensaje", "Daper Modificado ");
                   else request.setAttribute("mensaje", "Daper NO se ha podido modificar");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");
        rd.forward(request, response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

