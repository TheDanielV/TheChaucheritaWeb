package entidades;


import modelo.dao.DAOFactory;
import modelo.entidades.Usuario;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioTest {
    @Test
    public void userRegister(){
        Usuario nuevo = new Usuario();
        nuevo.setClave("123");
        nuevo.setNombre("TheAdmin");
        DAOFactory.getFactory().getUsuarioDAO().create(nuevo);
        DAOFactory.getFactory().getUsuarioDAO().getById(1);
        }

}