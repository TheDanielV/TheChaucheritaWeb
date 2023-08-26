package modelo.jpa;


import modelo.dao.CuentaDAO;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaTipo;

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
	public List<Cuenta> getCuentasIngreso() {
		String JPQL = "SELECT c FROM Cuenta c "
				+ "WHERE c.tipo= :tipo";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.INGRESO);
		return query.getResultList();
	}
	
	@Override
	public List<Cuenta> getCuentasEgreso() {
		String JPQL = "SELECT c FROM Cuenta c "
				+ "WHERE c.tipo= :tipo";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.GASTO);
		return query.getResultList();
	}


	@Override
	public List<Cuenta> getCuentasIngresoGasto() {
		String JPQL = "SELECT c FROM Cuenta c "
				+ "WHERE c.tipo= :tipo";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.INGRESOGASTO);
		return query.getResultList();
	}
	
	
	
	
	

}
