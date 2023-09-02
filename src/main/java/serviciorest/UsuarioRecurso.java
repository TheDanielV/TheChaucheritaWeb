package serviciorest;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modelo.dao.DAOFactory;
import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;


@Path("/usuarios")
public class UsuarioRecurso {

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarUsuario(@QueryParam("nombre") String nombre, @QueryParam("clave") String clave) {

        UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
        Usuario usuarioExistente = usuarioDAO.validarUsuarioParaRegistrar(nombre, clave);

        if (usuarioExistente != null) {
            usuarioDAO.create(usuarioExistente);
        } else {
            System.out.println("Usuario ya registrado.");
        }
    }

}