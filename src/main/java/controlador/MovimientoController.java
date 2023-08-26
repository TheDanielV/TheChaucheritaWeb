package controlador;


import modelo.dao.DAOFactory;
import modelo.entidades.Categoria;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@WebServlet("/MovimientoController")
public class MovimientoController extends HttpServlet {

	public MovimientoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null ? "registrar" : request.getParameter("ruta"));

		switch (ruta) {
			case "nuevoIngreso":
				renderIngreso(request,response);
				break;
			case "nuevoEgreso":
				renderEgreso(request,response);
				break;
			case "nuevaTransferencia":
				renderTransferencia(request,response);
				break;
			case "confirmarIngreso":
				registrarIngreso(request, response);
				break;
			case  "confirmarEgreso":
				registrarEegreso(request,response);
				break;
			case  "confirmarTransferencia":
				registrarTransferenci(request,response);
				break;

		}

	}

	private void registrarTransferenci(HttpServletRequest request, HttpServletResponse response) {
	}

	private void registrarEegreso(HttpServletRequest request, HttpServletResponse response) {
		//Recoleccion de datos para crear el movimiento
		Integer idCuenta = Integer.parseInt(request.getParameter("cuenta"));
		double monto = Double.parseDouble(request.getParameter("monto"));
		String descripcion = request.getParameter("desc");
		int idCategoria = Integer.parseInt(request.getParameter("categoria"));
		Date fecha = new Date();

	}

	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response) {

		//Recoleccion de datos para crear el movimiento
		Integer idCuenta = Integer.parseInt(request.getParameter("cuenta"));
		double monto = Double.parseDouble(request.getParameter("monto"));
		String descripcion = request.getParameter("desc");
		int idCategoria = Integer.parseInt(request.getParameter("categoriaID"));
		Date fecha = new Date();
		//Creacion del Objeto movimiento
		Movimiento movimiento = new Movimiento();
		movimiento.setMonto(monto);
		movimiento.setFecha(fecha);
		movimiento.setCuenta(DAOFactory.getFactory().getCuentaDAO().getById(idCuenta));
		movimiento.setDescripcion(descripcion);
		movimiento.setCategoria(Categoria.EDUCACION);


		//Guardado del movimiento
		DAOFactory.getFactory().getMovimientoDAO().crearIngreso(movimiento);
	}

	private void renderTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAll());
		request.getRequestDispatcher("/vista/ingreso.jsp").forward(request, response);
	}

	private void renderEgreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAll());
		request.getRequestDispatcher("/vista/ingreso.jsp").forward(request, response);

	}

	private void renderIngreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(Categoria.TRANSPORTE);
		categorias.add(Categoria.COMIDA);
		categorias.add(Categoria.Personal);
		request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAll());
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher("/vista/registrarIngreso.jsp").forward(request, response);

	}


}
