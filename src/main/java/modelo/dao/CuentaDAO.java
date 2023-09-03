package modelo.dao;

import modelo.entidades.Cuenta;
import modelo.entidades.Usuario;

import java.util.List;

public interface CuentaDAO extends GenericDAO<Cuenta, Integer> {

	public Double getTotalCuenta(int id_usuario, int id_cuenta);
	public List<Cuenta> getAllByID(Usuario propietario);
	
	
}
