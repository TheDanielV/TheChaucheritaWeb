package controlador;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//sdfbrgfbhreyivnreuivneruiv

@WebServlet("/InicioController")
public class InicioController extends HttpServlet {

    public InicioController() {
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
		case "ver":
			inicio(request, response);
			break;
		}
	}

	private void inicio(HttpServletRequest request, HttpServletResponse response) {
	}


}
