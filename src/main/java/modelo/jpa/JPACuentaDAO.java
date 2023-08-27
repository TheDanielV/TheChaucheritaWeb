package modelo.jpa;


import modelo.dao.CuentaDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.TipoCategoria;

import javax.persistence.Query;
import java.util.List;


public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	public JPACuentaDAO() {
		super(Cuenta.class);
	}

	@Override
	public Double getTotalCuenta(int id_cuenta) {
		return null;
	}

	@Override
	public List<Cuenta> getAllByID(int id_usuario) {
		Query query = em.createQuery("SELECT c FROM Cuenta c WHERE c.propietario = :propietario", Cuenta.class);
		query.setParameter("propietario", id_usuario);
		return query.getResultList();
	}


}
