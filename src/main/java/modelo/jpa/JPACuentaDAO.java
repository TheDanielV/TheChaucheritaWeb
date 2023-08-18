package modelo.jpa;


import modelo.dao.CuentaDAO;
import modelo.dto.CuentaDTO;
import modelo.entidades.Cuenta;
import modelo.entidades.CuentaTipo;

import javax.persistence.Query;
import java.util.List;


public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	public JPACuentaDAO() {
		super(Cuenta.class);
	}

	@Override
	public List<CuentaDTO> getConsolidadoCuentasIngreso(int mes) {
		String JPQL = "SELECT new modelo.dto.CuentaDTO( m.origen.id, m.origen.nombre, SUM(m.valor)) FROM Movimiento m "
				+ "WHERE m.origen.tipo= :tipo AND "
				+ "FUNC('MONTH', m.fecha)= :mes "
				+ "GROUP BY m.origen";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.INGRESO);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public List<CuentaDTO> getConsolidadoCuentsEgreso(int mes) {
		
		String JPQL = "SELECT new modelo.dto.CuentaDTO( m.destino.id, m.destino.nombre, SUM(m.valor)) FROM Movimiento m "
				+ "WHERE m.destino.tipo= :tipo AND "
				+ "FUNC('MONTH', m.fecha)= :mes "
				+ "GROUP BY m.destino";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.GASTO);
		query.setParameter("mes", mes);
		return query.getResultList();
	}

	@Override
	public List<Cuenta> getConsolidadoCuentasIngresoEgreso() {
		String JPQL = "SELECT c FROM Cuenta c "
				+ "WHERE c.tipo= :tipo";
		Query query = em.createQuery(JPQL);
		query.setParameter("tipo", CuentaTipo.INGRESOGASTO);
		return query.getResultList();
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
