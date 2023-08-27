package controlador;


import modelo.dao.DAOFactory;
import modelo.entidades.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

	private void registrarTransferenci(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario == null) {
			response.sendRedirect("LoginController?ruta=inicio");
		}

		//Recoleccion de datos para crear el movimiento
		Integer idCuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
		Integer idCuentaDestino = Integer.parseInt(request.getParameter("cuentaDestino"));
		double monto = Double.parseDouble(request.getParameter("monto"));
		String descripcion = request.getParameter("desc");
		Date fecha = new Date();
		//Creacion del Objeto movimiento(Movimiento de transferencia 1(un egreso))
		Movimiento movimientoEgreso = new Movimiento();
		movimientoEgreso.setMonto(monto);
		movimientoEgreso.setFecha(fecha);
		movimientoEgreso.setCuenta(DAOFactory.getFactory().getCuentaDAO().getById(idCuentaOrigen));
		movimientoEgreso.setDescripcion(descripcion);
		//TODO Cambiar por la categoria deseada
		movimientoEgreso.setCategoria(DAOFactory.getFactory().getCategoriaDAO().getCategoriaTransferencia());

		//Creacion del Objeto movimiento(Movimiento de transferencia 1(un egreso))
		Movimiento movimientoIngreso = new Movimiento();
		movimientoIngreso.setMonto(monto);
		movimientoIngreso.setFecha(fecha);
		movimientoIngreso.setCuenta(DAOFactory.getFactory().getCuentaDAO().getById(idCuentaDestino));
		movimientoIngreso.setDescripcion(descripcion);
		//TODO Cambiar por la categoria deseada
		movimientoIngreso.setCategoria(DAOFactory.getFactory().getCategoriaDAO().getCategoriaTransferencia());


		//Guardado del movimiento
		DAOFactory.getFactory().getMovimientoDAO().creaarTransferencia(movimientoIngreso,movimientoEgreso);
		response.sendRedirect("VerMovimientosController?ruta=dashboard");
	}

	private void registrarEegreso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario == null) {
			response.sendRedirect("LoginController?ruta=inicio");
		}
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
		//TODO: Cambiar por una forma de obtener dicha categoria
		movimiento.setCategoria(DAOFactory.getFactory().getCategoriaDAO().getById(idCategoria));


		//Guardado del movimiento
		DAOFactory.getFactory().getMovimientoDAO().crearEgreso(movimiento);
		response.sendRedirect("VerMovimientosController?ruta=dashboard");

	}

	private void registrarIngreso(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario == null) {
			response.sendRedirect("LoginController?ruta=inicio");
		}


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
		movimiento.setCategoria(DAOFactory.getFactory().getCategoriaDAO().getById(idCategoria));


		//Guardado del movimiento
		DAOFactory.getFactory().getMovimientoDAO().crearIngreso(movimiento);
		response.sendRedirect("VerMovimientosController?ruta=dashboard");
	}

	private void renderTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {
		request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAllByID(usuario));
		request.setAttribute("categorias", DAOFactory.getFactory().getCategoriaDAO().gellAllByCategoria(TipoCategoria.TRANSFERENCIA));
		request.getRequestDispatcher("/vista/registrarTransferencia.jsp").forward(request, response);
		}else response.sendRedirect("LoginController?ruta=inicio");
	}

	private void renderEgreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {
			request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAllByID(usuario));
			request.setAttribute("categorias", DAOFactory.getFactory().getCategoriaDAO().gellAllByCategoria(TipoCategoria.EGRESO));
			request.getRequestDispatcher("/vista/registrarEgreso.jsp").forward(request, response);
		}else response.sendRedirect("LoginController?ruta=inicio");
	}

	private void renderIngreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {
			request.setAttribute("cuentas", DAOFactory.getFactory().getCuentaDAO().getAllByID(usuario));
			request.setAttribute("categorias", DAOFactory.getFactory().getCategoriaDAO().gellAllByCategoria(TipoCategoria.INGRESO));
			request.getRequestDispatcher("/vista/registrarIngreso.jsp").forward(request, response);
			//TODO: Redirect a la visualizacion de dashbord?
		}else response.sendRedirect("LoginController?ruta=inicio");
	}


}
