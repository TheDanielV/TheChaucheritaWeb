package modelo.entidades;

import modelo.dao.DAOFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovimientoTest {
    @Test
    public void movmentDtoTest(){
        System.out.println(DAOFactory.getFactory().getMovimientoDAO().getCuentasConTotal(1));

    }
    @Test
    public void categoriaDTOTest(){
        System.out.println(DAOFactory.getFactory().getMovimientoDAO().getCategoriasConTotal(1));

    }
    @Test
    public void deleteTransferTestt(){
        DAOFactory.getFactory().getMovimientoDAO().deleteTransferencia(12);
    }

}