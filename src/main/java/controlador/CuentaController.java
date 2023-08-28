package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.DAOFactory;
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
			case "verCuentas":
				this.verCuentas(request,response);
			case "registrarCuenta":
				this.registrarCuenta(request,response);
			default:
				break;
		}
	}

	private void verCuentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos los parametros que nesecitamos y lo senviamos a la vista
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario+"ver la Cuenta");
		if(usuario != null) {
			request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAllByID(usuario));
			request.getRequestDispatcher("/vista/ListaCuentas.jsp").forward(request, response);
		}else response.sendRedirect("LoginController?ruta=inicio");

	}

	private void registrarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos los parametros que nesecitamos y lo senviamos a la vista

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario+"usuario en formulario de cuenta");
		if(usuario != null) {
			request.getRequestDispatcher("/vista/formularioAgregarCuenta.jsp").forward(request, response);
		}else response.sendRedirect("LoginController?ruta=inicio");

	}

	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		Integer idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
		if(usuario != null) {
				DAOFactory.getFactory().getCuentaDAO().deleteByID(idCuenta);
				// Redirigir a la vista que muestra todas las cuentas
				response.sendRedirect("CuentaController?ruta=verCuentas");
		}
    }

	private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario+"Este usuario se uso para crear la cuenta");
		if(usuario != null) {
			String nombreCuenta = request.getParameter("nombreCuenta");
			Cuenta cuenta = new Cuenta();

			cuenta.setPropietario(DAOFactory.getFactory().getUsuarioDAO().getById(usuario.getId()));
			cuenta.setNombre(nombreCuenta);
			DAOFactory.getFactory().getCuentaDAO().create(cuenta);
			response.sendRedirect("CuentaController?ruta=verCuentas");
		}else response.sendRedirect("LoginController?ruta=inicio");
}
}
