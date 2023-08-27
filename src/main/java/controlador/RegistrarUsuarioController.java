package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DAOFactory;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;

import java.io.IOException;

@WebServlet("/RegistrarUsuarioController")
public class RegistrarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "init" : request.getParameter("ruta");
		switch (ruta) {
			case "inicio":
				this.inicio(request, response);
				break;
			case "registrarUsuario":
				this.registrarUsuario(request, response);
				break;
			case "error":
				System.out.println("No debe llegar acá nunca ;(");
				break;
			default:
				break;
		}
	}

	private void inicio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/vista/registroUsuarios.jsp").forward(request, response);
	}

	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String nombre = request.getParameter("nombre");
		String clave = request.getParameter("clave");

		UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
		Usuario usuarioExistente = usuarioDAO.validarUsuarioParaRegistrar(nombre, clave);

		if (usuarioExistente == null) {
			Usuario nuevoUsuario = new Usuario(nombre, clave);
			usuarioDAO.create(nuevoUsuario);
			response.sendRedirect("LoginController?ruta=inicio");
		} else {
			request.setAttribute("mensajeError", "Usuario ya registrado.");
			request.getRequestDispatcher("/vista/registroUsuarios.jsp").forward(request, response);
		}
	}
}
