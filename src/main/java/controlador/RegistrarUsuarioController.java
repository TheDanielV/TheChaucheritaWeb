package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;

import java.io.IOException;

@WebServlet("/RegistrarUsuarioController")
public class RegistrarUsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.router(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.router(request, response);
    }

    private void router(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String rute = (request.getParameter("rute") == null) ? "init" : request.getParameter("rute");
        switch (rute) {
            case "init":
                this.init(request, response);
                break;
            case "saveUser":
                this.saveUser(request, response);
                break;
            // Add more cases as needed
        }
    }

    private void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/jsp/registroUsuarios.jsp");
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nombre = request.getParameter("nombre");
        String clave = request.getParameter("clave");
        
        UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
        Usuario existingUser = usuarioDAO.validarUsuarioParaRegistrar(nombre, clave);

        if (existingUser == null) {
            Usuario newUser = new Usuario(nombre, clave);
            usuarioDAO.create(newUser);
            response.sendRedirect("LoginController?rute=init");
        } else {
            request.setAttribute("errorMessage", "Usuario ya registrado");
            request.getRequestDispatcher("/jsp/registroUsuarios.jsp").forward(request, response);
        }
    }
}

