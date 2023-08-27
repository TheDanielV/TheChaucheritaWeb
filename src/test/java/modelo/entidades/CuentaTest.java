package modelo.entidades;

import modelo.dao.DAOFactory;
import org.junit.Test;

public class CuentaTest {
    @Test
    public void cuentaTest(){
        Cuenta cuenta = new Cuenta();
        Usuario usuario = new Usuario();
        usuario.setNombre("Daniel");
        usuario.setClave("daniel123");
        cuenta.setPropietario(usuario);
        cuenta.setNombre("Bolsillo");
        DAOFactory.getFactory().getCuentaDAO().create(cuenta);
    }
    @Test
    public void getAllCuentasTest(){
        System.out.println(DAOFactory.getFactory().getCuentaDAO().getAll());
    }

}