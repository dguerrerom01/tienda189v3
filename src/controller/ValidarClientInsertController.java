package controller;

import dao.clienteDAO.ClienteDAO;
import dao.clienteDAO.ClienteRoll;
import dao.cp.CPDAO;
import entity.ClienteEntity;
import validate.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/valiCliIn")
@MultipartConfig
public class ValidarClientInsertController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        DataInsertCliente  dataInsertCliente = null;

        try {
           dataInsertCliente = new DataInsertCliente(request);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

       /* ClienteEntity cliente = new ClienteEntity();

        // new GuardadorDeRequesParamsEnSession().guardarDatosSesion(request,session);

        try {

           // new CreadorDeObjetosConSessionAtributes(session,cliente);

        }  catch (IllegalAccessException | InvocationTargetException e) {

            System.out.println("Error creando el objeto");
        }

        /*
        Esto seria otra opcion para crear cliente con los valores del session
         pero el creadorDeObjetoConSession es reutilizable y no repite codigo
        try {
            ClienteEntity clienteConConstructorPropio = new ClienteEntity(session);
        } catch (IllegalAccessException e) {
            System.out.println("error creando cliente con constructor session");
        }
        */
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        String error = "";

        List<IValidacion> validador = new ArrayList<IValidacion>();

        RequestDispatcher rd = request.getRequestDispatcher("clientInsert.jsp");

        boolean continuar = true;
        if(continuar) continuar = new ValidacionDNINIECIF(dataInsertCliente.getNifCliente()).validar();
        if(continuar) continuar = new ValidacionLetrasConEspacio(dataInsertCliente.getNombreCliente()).validar();
        if(continuar) continuar = new ValidacionLongitud(dataInsertCliente.getNombreCliente(), 3, 50).validar();
        if(continuar) continuar = new ValidacionLetrasConEspacio(dataInsertCliente.getApellidosCliente()).validar();
        if(continuar) continuar = new ValidacionLongitud(dataInsertCliente.getApellidosCliente(), 3, 100).validar();
        if(continuar) continuar = new ValidacionCodigoPostal(dataInsertCliente.getCodigoPostalClient()).validar();
        if(continuar) continuar = new ValidarDomicilio(dataInsertCliente.getDomicilioCliente()).validar();
        if(continuar) continuar = new ValidacionLongitud(dataInsertCliente.getDomicilioCliente(), 2, 100).validar();
        if(continuar) continuar = new ValidacionTelefonoSpain(dataInsertCliente.getTelefonoCliente()).validar();
        if(continuar) continuar = new ValidacionTelefonoSpain(dataInsertCliente.getMovilCliente()).validar();
        if(continuar) continuar = new ValidacionFecha(dataInsertCliente.getFechaNacimiento()).validar();
        if(continuar) continuar = new ValidacionSexo(dataInsertCliente.getSexoCliente()).validar();
        if(continuar) continuar = new ValidacionEmail(dataInsertCliente.getEmailCliente()).validar();
        if(continuar) continuar = new ValidacionUsuario(dataInsertCliente.getUsuarioCliente()).validar();
        if(continuar) continuar = new ValidacionPassword(dataInsertCliente.getPasswordCliente()).validar();

        ClienteRoll clienteRoll = new ClienteRoll();

        CPDAO cpdao = new CPDAO(clienteRoll.getUsuario(), clienteRoll.getPass());

        try {
            if (!cpdao.check_cp(dataInsertCliente.getCodigoPostalClient())) {

                error = "Codigo Postal Inexistente";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        if (error.length() > 0) {
            request.setAttribute("error", error);
        } else {
            request.setAttribute("error", "Todo Correcto");
            clientFotoLoad(request, response);
            dataInsertCliente.setImagenCliente(dataInsertCliente.getNifCliente() + ".png");

            rd = request.getRequestDispatcher("index.jsp");

            /*
          //    cliente para BD sin procedure
            ClienteDAO clienteDAO = new ClienteDAO();
           if (clienteDAO.add_cliente(cliente)>0){
               request.setAttribute("mensaje", "Cliente add");
           }
           else request.setAttribute("mensaje", "Cliente NO add");
           */

            //    cliente para BD CON procedure
            ClienteDAO clienteDAO = new ClienteDAO();
            if (clienteDAO.add_cliente_procedure(dataInsertCliente.getClienteEntity())) {
                request.setAttribute("mensaje", "Cliente add");
            } else request.setAttribute("mensaje", "Cliente NO add");

        }

        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return "fotoSin.jpg";
    }

    private void clientFotoLoad(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Part filePart = request.getPart("imagenCliente");
        String fileName = getFileName(filePart);
        String dniCliente = request.getParameter("dniCliente");

        if (fileName.length() > 2) {

            fileName = dniCliente + ".png";

            String path = getServletContext().getRealPath("img/fotoClient/");

            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileOutputStream fs = new FileOutputStream(new File(path + fileName));
            BufferedOutputStream buf = new BufferedOutputStream(fs);

            InputStream fileContent = filePart.getInputStream();
            BufferedInputStream bufIN = new BufferedInputStream(fileContent);

            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = bufIN.read(buffer)) != -1) {
                buf.write(buffer, 0, bytesRead);
            }

            buf.close();
            bufIN.close();
        }
    }
}

