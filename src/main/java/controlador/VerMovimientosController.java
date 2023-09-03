package controlador;




import modelo.dao.DAOFactory;
import modelo.dto.CategoriaTotalDTO;
import modelo.entidades.Movimiento;
import modelo.entidades.TipoCategoria;
import modelo.entidades.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/VerMovimientosController")
public class VerMovimientosController extends HttpServlet {

    public VerMovimientosController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ruteador(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ruteador(request, response);
	}
	
	private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ruta = (request.getParameter("ruta")==null?"ver":request.getParameter("ruta"));
		
		switch(ruta) {
			case "dashboard":
				renderDashboard(request,response);
			case "verPorCuenta":
				verPorCuenta(request, response);
				break;
			case "verPorCategoria":
				verPorCategoria(request,response);
			break;
		}
	}

	private void renderDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		if(usuario == null) {
			response.sendRedirect("LoginController?ruta=inicio");
		}else {
			String mesDado = request.getParameter("mesDado");
			// Crear una lista de meses



			// Crea un formato para el nombre del mes en el idioma deseado (por ejemplo, español)
			SimpleDateFormat formatoMes = getSimpleDateFormat();
			String nombreMes;
			//Obtendremos las categorias dadas por un mes
			List<CategoriaTotalDTO> categorias = new ArrayList<>();

			if (mesDado == null){
				Date fechaActual = new Date();
				// Obtén el nombre del mes en formato "Enero"
				 nombreMes = formatoMes.format(fechaActual);

			}else {
				nombreMes = mesDado;
			}

			for (CategoriaTotalDTO caategoria:
					DAOFactory.getFactory().getMovimientoDAO().getCategoriasConTotal(usuario.getId())) {
				if(formatoMes.format(caategoria.getMes()).equals(nombreMes) && !caategoria.getNombre().equals("Transferencia")){
					categorias.add(caategoria);
				}
			}
			request.setAttribute("mesDado", nombreMes);
			request.setAttribute("meses", getMeses());
			request.setAttribute("cuentas", DAOFactory.getFactory().getMovimientoDAO().getCuentasConTotal(usuario.getId()));
			request.setAttribute("categorias", categorias);
			request.getRequestDispatcher("/vista/dashboard.jsp").forward(request,response);
		}
	}

	private void verPorCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {
			
			Integer idCategoria = null;

			try {
				idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
			} catch (Exception e) {
				idCategoria = null;
				System.out.println("idCategoria quedo null por que? - Error: " + e);
			}

			String mesDado =request.getParameter("mesDado");

			SimpleDateFormat formatoMes = getSimpleDateFormat();
			String nombreMes;
			//Obtendremos las categorias dadas por un mes
			List<Movimiento> movimientos = new ArrayList<>();

			if (mesDado == null){
				Date fechaActual = new Date();
				// Obtén el nombre del mes en formato "Enero"
				nombreMes = formatoMes.format(fechaActual);

			}else {
				nombreMes = mesDado;
			}

			for (Movimiento movimiento:
					DAOFactory.getFactory().getMovimientoDAO().getAllByCategoria(idCategoria)) {
				if(formatoMes.format(movimiento.getFecha()).equals(nombreMes) && movimiento.getCategoria().getTipo() != TipoCategoria.TRANSFERENCIA){
					movimientos.add(movimiento);
				}
			}
			request.setAttribute("mesDado", nombreMes);
			request.setAttribute("movimientos", movimientos);
			request.getRequestDispatcher("/vista/movimientoCategoria.jsp").forward(request,response);

		}else response.sendRedirect("LoginController?ruta=inicio");
	}

	private void verPorCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {

			Integer idCuenta = null;

			try {
				idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
			} catch (Exception e) {
				idCuenta = null;
				System.out.println("idCuenta quedo null por que? - Error: " + e);
			}

			String mesDado = request.getParameter("mesDado");

			SimpleDateFormat formatoMes = getSimpleDateFormat();
			String nombreMes;
			//Obtendremos las categorias dadas por un mes
			List<Movimiento> movimientos = new ArrayList<>();

			if (mesDado == null){
				Date fechaActual = new Date();
				// Obtén el nombre del mes en formato "Enero"
				nombreMes = formatoMes.format(fechaActual);

			}else {
				nombreMes = mesDado;
			}

			for (Movimiento movimiento:
					DAOFactory.getFactory().getMovimientoDAO().getAllByCuenta(idCuenta)) {
				if(formatoMes.format(movimiento.getFecha()).equals(mesDado)){
					movimientos.add(movimiento);
				}
			}
			request.setAttribute("mesDado", nombreMes);
			request.setAttribute("movimientos", movimientos);
			request.getRequestDispatcher("/vista/movimientoCuenta.jsp").forward(request,response);

		}else response.sendRedirect("LoginController?ruta=inicio");
	}

	private static SimpleDateFormat getSimpleDateFormat() {
		SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
		return formatoMes;
	}
	private static List<String> getMeses(){
		List<String> meses = new ArrayList<>();
		meses.add("enero");
		meses.add("febrero");
		meses.add("marzo");
		meses.add("abril");
		meses.add("mayo");
		meses.add("junio");
		meses.add("julio");
		meses.add("agosto");
		meses.add("septiembre");
		meses.add("octubre");
		meses.add("noviembre");
		meses.add("diciembre");
		return  meses;
	}


}
