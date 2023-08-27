package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
import modelo.entidades.Usuario;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.ruteador(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.ruteador(request, response);
    }

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "inicio" : request.getParameter("ruta");

		switch (ruta) {
		case "inicio":
			this.inicio(request, response);
			break;
		case "login":
			this.login(request, response);
			break;
		case "registrar":
			this.registrar(request, response);
			break;
		case "salir":
			this.salir(request, response);
			break;
		case "error":
		System.out.println("No debe llegar acá nunca ;(");
			break;
		default:
			break;
		}
	}

	private void inicio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/vista/login.jsp").forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nombre = request.getParameter("nombre");
        String clave = request.getParameter("clave");

		Usuario usuarioAutenticado = DAOFactory.getFactory().getUsuarioDAO().autorizar(nombre, clave);
		 
		if (usuarioAutenticado != null) {

			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogeado", usuarioAutenticado);
			response.sendRedirect("VerMovimientoController?ruta=ejemploTocaCambiar");
			return;

		} else {

			request.setAttribute("mensajeError", "Nombre o clave incorrectas.");
            request.getRequestDispatcher("/vista/login.jsp").forward(request, response);
		}
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("RegistrarUsuarioController?ruta=inicio");
	}

	private void salir(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getSession().invalidate();
		request.getRequestDispatcher("/vista/login.jsp").forward(request, response);
	}
}
