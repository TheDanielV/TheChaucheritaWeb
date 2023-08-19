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

	private void ruteador(HttpServletRequest request, HttpServletResponse response) {
		String ruta = (request.getParameter("ruta") == null ? "ver" : request.getParameter("ruta"));

		switch (ruta) {
			case "ver":
				verResumen(request, response);
				break;
			case "nuevoIngreso":
				crearIngreso(request, response);
				break;
			case "nuevoEgreso":
				crearEgreso(request, response);
				break;
			case "guardarIngreso":
				guardarIngreso(request, response);
				break;
			case "guardarEgreso":
				guardarEgreso(request, response);
				break;
			case "nuevoIngresoEgreso":
				crearIngresoEgreso(request, response);
				break;

		}

	}

	private void crearIngresoEgreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void guardarEgreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void guardarIngreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void crearEgreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void crearIngreso(HttpServletRequest request, HttpServletResponse response) {

	}

	private void verResumen(HttpServletRequest request, HttpServletResponse response) {

	}
}
