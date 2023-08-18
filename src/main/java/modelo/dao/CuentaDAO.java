package modelo.dao;

import modelo.dto.CuentaDTO;
import modelo.entidades.Cuenta;

import java.util.List;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer> {

	public List<CuentaDTO> getConsolidadoCuentasIngreso(int mes);
	public List<CuentaDTO> getConsolidadoCuentsEgreso(int mes);
	public List<Cuenta> getConsolidadoCuentasIngresoEgreso();
	
	public List<Cuenta> getCuentasIngreso();
	public List<Cuenta> getCuentasEgreso();
	public List<Cuenta> getCuentasIngresoGasto();
	
	
}
