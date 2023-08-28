package controlador;




import modelo.dao.DAOFactory;
import modelo.entidades.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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
			//TODO: Logica que permita obtener los datos del dto y mandarlo al dashboard
			request.setAttribute("cuentas", DAOFactory.getFactory().getMovimientoDAO().getCuentasConTotal(usuario.getId()));
			request.setAttribute("categorias", DAOFactory.getFactory().getMovimientoDAO().getCategoriasConTotal(usuario.getId()));
			request.getRequestDispatcher("/vista/dashboard.jsp").forward(request,response);
		}
	}

	private void verPorCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {
			Integer idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
			System.out.println(DAOFactory.getFactory().getMovimientoDAO().getAllByCuenta(idCategoria));
			request.setAttribute("movimientos", DAOFactory.getFactory().getMovimientoDAO().getAllByCategoria(idCategoria));
			request.getRequestDispatcher("/vista/movimientoCategoria.jsp").forward(request,response);

		}else response.sendRedirect("LoginController?ruta=inicio");
	}

	private void verPorCuenta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogeado");
		System.out.println(usuario);
		if(usuario!=null) {

			Integer idCuenta = Integer.parseInt(request.getParameter("idCuenta"));

			System.out.println(DAOFactory.getFactory().getMovimientoDAO().getAllByCuenta(idCuenta));

			request.setAttribute("movimientos", DAOFactory.getFactory().getMovimientoDAO().getAllByCuenta(idCuenta));
			request.getRequestDispatcher("/vista/movimientoCuenta.jsp").forward(request,response);

		}else response.sendRedirect("LoginController?ruta=inicio");
	}


}
