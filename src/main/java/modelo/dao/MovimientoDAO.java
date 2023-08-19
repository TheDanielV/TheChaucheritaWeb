package modelo.dao;

import modelo.dto.CuentaTotalDTO;
import modelo.entidades.Movimiento;
import modelo.entidades.Usuario;

import java.util.List;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer> {

    public List<CuentaTotalDTO> getCuentasConTotal(int id_usuario);
    public List<CategoriaTotalDTO> getCategoriasConTotal(int id_usuario);

}
