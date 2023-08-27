package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			default:
				break;
		}
	}

	private void eliminarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idCuenta = request.getParameter("id_cuenta"); // Suponiendo que el identificador se pasa como "id_cuenta"
        
        if (idCuenta != null) {
            int id_cuenta = idCuenta;
            cuentaDAO.delete(id_cuenta);  
            
            // Redirigir a la vista que muestra todas las cuentas
            response.sendRedirect("/jsp/ListaCuentas.jsp");
        } else {
            // Manejo de error si no se proporciona un ID válido
            request.setAttribute("error", "ID de cuenta no proporcionado");
            request.getRequestDispatcher("jsp/Error.jsp").forward(request, response);
        }
    }

	private void crearCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCuenta = request.getParameter("nombreCuenta");

		CuentaDAO cuentaDAO = DAOFactory.getFactory().getCuentaDAO();
		Cuenta cuentaCreada = cuentaDAO.crearNuevaCuenta(nombreCuenta);
        
        if (nombreCuenta != null && !nombreCuenta.trim().isEmpty()) {
            Cuenta nuevaCuenta = new Cuenta(nombreCuenta);
            cuentaDAO.create(nuevaCuenta);  
            
            response.sendRedirect("VerMovimientosController?ruta=dashboard"); 
        } else {
            // Manejar el caso donde el nombreCuenta no es válido o ya esta creado, quizá redirigir a una página de error o mostrar un mensaje
            request.setAttribute("mensajeError", "Error en la cuenta");
            request.getRequestDispatcher("/jsp/FormularioAgregarCuenta.jsp").forward(request, response);
        }
	
}
}
