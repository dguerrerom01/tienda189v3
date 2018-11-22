package controller;

import cliente.DataLoginCliente;
import cliente.DataPersonCliente;
import dao.clienteDAO.ClienteDAO;
import dao.clienteDAO.ClienteRoll;
import dao.cp.CPDAO;
import entity.ClienteEntity;
import error.EstadoError;
import cliente.ComandosDaperCliente;
import cliente.ComandosLoginCliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/valiCliIn")
@MultipartConfig
public class ValidarClientInsertController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    HttpSession session;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");

        RequestDispatcher rd = request.getRequestDispatcher("cliente/clientInsert.jsp");

        DataPersonCliente dataPersonCliente = null;
        try {
            dataPersonCliente = new DataPersonCliente(request);

        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        DataLoginCliente dataLoginCliente = null;
        try {
            dataLoginCliente = new DataLoginCliente(request);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> listaErrores = new ArrayList<Integer>();

        ComandosDaperCliente comandosDaperCliente = new ComandosDaperCliente(dataPersonCliente.getDaperClienteEntity());
        listaErrores = comandosDaperCliente.getComands();

        ComandosLoginCliente comandosLogin = new ComandosLoginCliente(dataLoginCliente.getLoginClienteEntity());
        listaErrores.addAll(comandosLogin.getCommands());

        String mensaje = "";

        for(Integer error:listaErrores){
            for (EstadoError estado : EstadoError.values()){
                if(error == estado.getId() & error != 0) mensaje = mensaje.concat(estado.getMsg());
            }
        }
        request.setAttribute("mensaje", mensaje);

        if(mensaje.equals("")) {

            ClienteRoll clienteRoll = new ClienteRoll();

            CPDAO cpdao = new CPDAO(clienteRoll.getUsuario(), clienteRoll.getPass());
// Mejorarlo
            try {
                if (!cpdao.check_cp(dataPersonCliente.getCodigoPostalCliente())) {

                    request.setAttribute("mensaje", "Codigo Postal Inexistente");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            clientFotoLoad(request, response);

            dataPersonCliente.setImagenCliente(dataPersonCliente.getNifCliente() + ".png");

 // Mejorarlo trasvase de una entidad a otra
             ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setNifCliente(dataPersonCliente.getNifCliente());
            clienteEntity.setApellidosCliente(dataPersonCliente.getApellidosCliente());
            clienteEntity.setNombreCliente(dataPersonCliente.getNombreCliente());
            clienteEntity.setDomicilioCliente(dataPersonCliente.getDomicilioCliente());
            clienteEntity.setCodigoPostalCliente(dataPersonCliente.getCodigoPostalCliente());
            clienteEntity.setFechaNacimiento(dataPersonCliente.getFechaNacimiento().toString());
            clienteEntity.setTelefonoCliente(dataPersonCliente.getTelefonoCliente());
            clienteEntity.setMovilCliente(dataPersonCliente.getMovilCliente());
            clienteEntity.setSexoCliente(dataPersonCliente.getSexoCliente());
            clienteEntity.setEmailCliente(dataPersonCliente.getEmailCliente());
            clienteEntity.setImagenCliente(dataPersonCliente.getImagenCliente());
            clienteEntity.setUsuarioCliente(dataLoginCliente.getUsuarioCliente());
            clienteEntity.setPasswordCliente(dataLoginCliente.getPasswordCliente());

            ClienteDAO clienteDAO = new ClienteDAO();

            if (clienteDAO.add_cliente_procedure(clienteEntity)) {
                request.setAttribute("mensaje", "Cliente add");
                rd = request.getRequestDispatcher("cliente/clienteIndex.jsp");
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
        String dniCliente = request.getParameter("nifCliente");

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

