package controlador;


import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/RegistrarMovimientosController")
public class RegistrarMovimientosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrarMovimientosController() {
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

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = (request.getParameter("ruta") == null ? "ver" : request.getParameter("ruta"));

		switch (ruta) {
		case "ver":
			showDashboard(request, response);
			break;
		case "nuevoIngreso":
			crearIngreso(request, response);
			break;
		case "nuevoEgreso":
			crearEgreso(request, response);
			break;
		case "guardarIngreso":
			try {
				guardarIngreso(request, response);
			} catch (ParseException e) {
				mostrarError(request, response);
				e.printStackTrace();
			}
			break;
		case "guardarEgreso":
			try {
				guardarEgreso(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				mostrarError(request, response);
				e.printStackTrace();
			}
			break;
		case "nuevoIngresoEgreso":
			crearIngresoEgreso(request, response);
			break;
		case "guardarIngresoEgreso":
			try {
				guardarIngresoEgreso(request, response);
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				mostrarError(request, response);
				e.printStackTrace();
			}
			break;
			
		}
	}

	

	private void crearIngresoEgreso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cuenta> cuentasIngresoGasto = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresoGasto();
		request.setAttribute("cuentasIngresoGasto", cuentasIngresoGasto);

		request.getRequestDispatcher("/jsp/traspaso.jsp").forward(request, response);
		
	}

	private void guardarIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException {

		// Obtener Datos
		int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
		int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
		double valor = Double.parseDouble(request.getParameter("valor"));
		String concepto = request.getParameter("concepto");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		Movimiento ingreso = new Movimiento();

		fecha = formatoFecha.parse(request.getParameter("fecha"));

		// Llamar al modelo
		Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getById(idCuentaOrigen);
		Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getById(idCuentaDestino);

		ingreso.setOrigen(cuentaOrigen);
		ingreso.setDestino(cuentaDestino);
		ingreso.setValor(valor);
		//ingreso.setConcepto(concepto);
		ingreso.setFecha(fecha);

		DAOFactory.getFactory().getMovimientoDAO().create(ingreso);

		cuentaOrigen.setTotal(cuentaOrigen.getTotal() + valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaOrigen);

		cuentaDestino.setTotal(cuentaDestino.getTotal() + valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDestino);

		this.showDashboard(request, response);

	}

	private void guardarEgreso(HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServletException, IOException {

		// Obtener Datos
		int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
		int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
		double valor = Double.parseDouble(request.getParameter("valor"));
		String concepto = request.getParameter("concepto");
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		Movimiento ingreso = new Movimiento();

		fecha = formatoFecha.parse(request.getParameter("fecha"));

		// Llamar al modelo
		Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getById(idCuentaOrigen);
		Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getById(idCuentaDestino);

		ingreso.setOrigen(cuentaOrigen);
		ingreso.setDestino(cuentaDestino);
		ingreso.setValor(valor);
		//ingreso.setConcepto(concepto);
		ingreso.setFecha(fecha);

		if (cuentaOrigen.getTotal() < valor) {
			throw new IOException("a");
		}
		
		DAOFactory.getFactory().getMovimientoDAO().create(ingreso);

		cuentaOrigen.setTotal(cuentaOrigen.getTotal() - valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaOrigen);

		cuentaDestino.setTotal(cuentaDestino.getTotal() - valor);
		DAOFactory.getFactory().getCuentaDAO().update(cuentaDestino);

		showDashboard(request, response);
	}
	private void guardarIngresoEgreso(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Obtener Datos
				int idCuentaOrigen = Integer.parseInt(request.getParameter("idCuentaOrigen"));
				int idCuentaDestino = Integer.parseInt(request.getParameter("idCuentaDestino"));
				double valor = Double.parseDouble(request.getParameter("valor"));
				String concepto = request.getParameter("concepto");
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				Date fecha = null;
				Movimiento ingreso = new Movimiento();

				fecha = formatoFecha.parse(request.getParameter("fecha"));

				// Llamar al modelo
				
				Cuenta cuentaOrigen = DAOFactory.getFactory().getCuentaDAO().getById(idCuentaOrigen);
				Cuenta cuentaDestino = DAOFactory.getFactory().getCuentaDAO().getById(idCuentaDestino);
				//if(cuentaOrigen.getNombre().equals(cuentaDestino.getNombre())) {
					//System.out.println("si entro");
					//throw new MovimientoException("El movimiento debe ser entre cuentas diferentes");
				//}
				ingreso.setOrigen(cuentaOrigen);
				ingreso.setDestino(cuentaDestino);
				ingreso.setValor(valor);
				//ingreso.setConcepto(concepto);
				ingreso.setFecha(fecha);
				
				if (cuentaOrigen.getTotal() < valor) {
					throw new IOException();
				}
				
				DAOFactory.getFactory().getMovimientoDAO().create(ingreso);

				cuentaOrigen.setTotal(cuentaOrigen.getTotal() - valor);
				DAOFactory.getFactory().getCuentaDAO().update(cuentaOrigen);

				cuentaDestino.setTotal(cuentaDestino.getTotal() + valor);
				DAOFactory.getFactory().getCuentaDAO().update(cuentaDestino);

				showDashboard(request, response);
		
	}

	private void showDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("DashboardController?ruta=ver").forward(request, response);
	}

	private void crearIngreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cuenta> cuentasIngreso = DAOFactory.getFactory().getCuentaDAO().getCuentasIngreso();
		List<Cuenta> cuentasIngresoGasto = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresoGasto();

		request.setAttribute("cuentasIngreso", cuentasIngreso);
		request.setAttribute("cuentasIngresoGasto", cuentasIngresoGasto);

		request.getRequestDispatcher("/jsp/ingreso.jsp").forward(request, response);

	}

	private void crearEgreso(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Cuenta> cuentasIngresoGasto = DAOFactory.getFactory().getCuentaDAO().getCuentasIngresoGasto();
		List<Cuenta> cuentasEgreso = DAOFactory.getFactory().getCuentaDAO().getCuentasEgreso();

		request.setAttribute("cuentasIngresoGasto", cuentasIngresoGasto);
		request.setAttribute("cuentasEgreso", cuentasEgreso);

		request.getRequestDispatcher("/jsp/egreso.jsp").forward(request, response);

	}

	private void mostrarError(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	}

}
