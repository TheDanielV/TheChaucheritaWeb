package modelo.entidades;

import modelo.dao.DAOFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovimientoTest {
    @Test
    public void getMovmentTest(){
        System.out.println(DAOFactory.getFactory().getMovimientoDAO().getById(2).getCategoria());

    }

}