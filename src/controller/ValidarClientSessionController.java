package controller;

import cliente.ComandoValidarLoginCliente;
import cliente.DataLoginCliente;
import dao.clienteDAO.ClienteDAO;
import error.Error;
import reflexion.ObjectTransferSession;
import util.ErrorManager;

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
import java.util.Date;

@WebServlet("/valiCliSesion")
public class ValidarClientSessionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        String opcion = request.getParameter("opcion");

        ClienteDAO clienteDAO = null;
        try {
            clienteDAO = new ClienteDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


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
        ComandoValidarLoginCliente comandoValidarLoginCliente = new ComandoValidarLoginCliente(dataLoginCliente.getLoginClienteEntity());

        ArrayList<Error> listaErrores = comandoValidarLoginCliente.getCommands();

// Comprobamos si No hay errores de validación
        if (listaErrores.isEmpty()) {
// Buscamos al cliente
            String nifLogin = clienteDAO.get_nif_login(dataLoginCliente.getLoginClienteEntity());
// Comprobamos si es un cliente
            if (!nifLogin.equals("null")) {

                session.setAttribute("nifCliente", nifLogin);

                dataLoginCliente.setNifCliente(nifLogin);

                request.setAttribute("mensaje", ("Saludo: Hola ").concat(nifLogin));



// Si opcion es modificar sus datos personales generamos los datos para luego mostrarlo en la vista
                if (opcion.equals("clientUpdateDaper")) {

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

            } else { // No se ha identificado correctamente
                dataLoginCliente.incrementarIntento();
                if (dataLoginCliente.disponibilidadIntento()) {
                    request.setAttribute("mensaje", "Cliente: Intentalo otra vez");
                } else {
                        if (opcion.equals("clienteIndex")) {
                            session.setAttribute("horaBloqueo", new Date());
                            request.setAttribute("mensaje", "Cliente: Intentos Agotados");
                            rd = request.getRequestDispatcher("cliente/clientBlocking.jsp");
                        }   else {
                            try {
                                // Hay que bloquearlo en BBDD
                                clienteDAO.locked_client(dataLoginCliente.getNifCliente());
                                // enviar email
                                rd = request.getRequestDispatcher("Mail");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
            }
        } else {
            request.setAttribute("mensaje", ErrorManager.getErrorMessages(listaErrores));
        }
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }


}
