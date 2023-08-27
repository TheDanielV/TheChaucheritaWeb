package modelo.dao;

import modelo.entidades.Cuenta;

import java.util.List;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer> {

	public Double getTotalCuenta(int id_cuenta);
	public List<Cuenta> getAllByID(int id_usuario);
	
	
}
