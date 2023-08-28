package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.CuentaDAO;
import modelo.dao.DAOFactory;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

@WebServlet("/CuentaController")
public class CuentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CuentaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ruteador(request, response);

	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String ruta = (request.getParameter("ruta") == null) ? "iniciar" : request.getParameter("ruta");
		switch (ruta) {
			case "crearCuenta":
				this.crearCuenta(request, response);
				break;
			case "eliminarCuenta":
				this.eliminarCuenta(request, response);
				break;
			case "error":
				System.out.println("No debe llegar acá nunca ;(");
				break;
			case "verCuentas":
				this.verCuentas(request,response);
			case "mostrarFormularioCuentas":
				this.mostrarFormularioCuentas(request,response);
			default:
				break;
		}
	}

	private void verCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos los parametros que nesecitamos y lo senviamos a la vista

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		if(usuario != null) {
			request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAllByID(usuario));
			request.getRequestDispatcher("/vista/ListaCuentas.jsp").forward(request, response);
		}else response.sendRedirect("LoginController?ruta=inicio");

	}

	private void mostrarFormularioCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos los parametros que nesecitamos y lo senviamos a la vista

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		if(usuario != null) {
			request.getRequestDispatcher("/vista/formularioAgregarCuenta.jsp").forward(request, response);
		}else response.sendRedirect("LoginController?ruta=inicio");

	}

	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		Integer idCuenta = Integer.parseInt(request.getParameter("id_cuenta")); // Suponiendo que el identificador se pasa como "id_cuenta"
		if(usuario != null) {
			if (idCuenta != null) {
				DAOFactory.getFactory().getCuentaDAO().deleteByID(idCuenta);
				// Redirigir a la vista que muestra todas las cuentas
				response.sendRedirect("/vista/ListaCuentas.jsp");
			} else {
				// Manejo de error si no se proporciona un ID válido
				request.setAttribute("error", "ID de cuenta no proporcionado");
				request.getRequestDispatcher("/vista/Error.jsp").forward(request, response);
			}
		}
    }

	private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario != null) {
			String nombreCuenta = request.getParameter("nombreCuenta");
			Cuenta cuenta = new Cuenta();
			cuenta.setNombre(nombreCuenta);
			cuenta.setPropietario(usuario);

			if (nombreCuenta != null && !nombreCuenta.trim().isEmpty()) {
				DAOFactory.getFactory().getCuentaDAO().create(cuenta);
				response.sendRedirect("VerMovimientosController?ruta=dashboard");
			} else {
				// Manejar el caso donde el nombreCuenta no es válido o ya esta creado, quizá redirigir a una página de error o mostrar un mensaje
				request.setAttribute("mensajeError", "Error en la cuenta");
				request.getRequestDispatcher("/vista/formularioAgregarCuenta.jsp").forward(request, response);
			}
		}else response.sendRedirect("LoginController?ruta=inicio");
}
}
