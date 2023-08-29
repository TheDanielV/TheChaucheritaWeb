package modelo.entidades;

import modelo.dao.DAOFactory;
import org.junit.Assert;
import org.junit.Test;

public class CuentaTest {
    @Test
    public void cuentaTest(){
        Cuenta cuenta = new Cuenta();
        cuenta.setPropietario(DAOFactory.getFactory().getUsuarioDAO().getById(1));
        cuenta.setNombre("Banco");
        DAOFactory.getFactory().getCuentaDAO().create(cuenta);

    }
    @Test
    public void getAllCuentasTest(){
        System.out.println(DAOFactory.getFactory().getCuentaDAO().getAll());
    }
    @Test
    public void removeCuentaTest(){
        DAOFactory.getFactory().getCuentaDAO().deleteByID(4);
    }

}