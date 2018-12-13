package email;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Desbloquear")
public class DesbloquearServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String mensaje = "";
        // System.out.println("email in desbloqueo: " + (String) session.getAttribute("email")); es otra session
        if(id == null){ // || id.isEmpty()
             mensaje = "Errror con CLAVE BLOQueo vacia";
        }
      else {
           //if( new UsuarioDAO().unlock_user((String) session.getAttribute("email"))){
          //  if( new UsuarioDAO().unlock_user(email)){

               mensaje = "Usuario desbloqueado";
         //  } else mensaje = "Error con unlock_user";
           // new UsuarioDAO().unblockUsuario(id);//<--
        }


       // response.getWriter().write(mensaje);

    }
}
