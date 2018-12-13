package email;


import dao.clienteDAO.ClienteDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Luciano on 12/12/2018.
 */

@WebServlet("/checkBloqueo")
public class CheckBloqueo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensaje = "";
        String email = "";
        String mipassword = "";
        String claveBloqueo = "";
        String claveBBDD = "";
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            claveBBDD = clienteDAO.getClaveBloqueo((String) session.getAttribute("usuarioCliente"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
        /*
        String mensaje = "";
        String email = "";
        String mipassword = "";
        String claveBloqueo = "";
        String claveBBDD = "";
        int perfil = 0;
        String miCliente = request.getParameter("json");
       //JSONParser parser = new JSONParser();
        Object obj = null;
        try {
           // obj = parser.parse(miCliente);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;

        email = (String) jsonObject.get("loginUsuario");
        mipassword = (String) jsonObject.get("loginPassword");
        claveBloqueo = (String) jsonObject.get("claveBloqueo");

        try {
            claveBBDD = new UsuarioDAO().getClave(email);
            perfil = new UsuarioDAO().get_idEstado(email, mipassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(claveBBDD.equalsIgnoreCase(claveBloqueo)){
            // a desbloquear
            if( new UsuarioDAO().unlock_user(email)) {
                for (Estado estado : Estado.values()) {
                    if (perfil == estado.getId()) {
                        session.setAttribute("pagina", estado.getMsg());
                    }
                }
                mensaje = "claveBloqueoOk";
            }
            else mensaje = "Error con unlock_user";

        }else mensaje = "NO coincide La clave de Bloqueo";



        response.getWriter().write(mensaje);


        */







    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }
}
