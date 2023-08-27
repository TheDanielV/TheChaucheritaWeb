package modelo.jpa;

import modelo.dao.MovimientoDAO;
import modelo.dto.CategoriaTotalDTO;
import modelo.dto.CuentaTotalDTO;
import modelo.entidades.Movimiento;
import modelo.entidades.TipoMovimiento;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("ALL")
public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}


	@Override
	public List<CuentaTotalDTO> getCuentasConTotal(int id_usuario) {
		String consulta = "SELECT m.cuenta.id, m.cuenta.nombre, sum(m.monto) as total from movimiento m where m.cuenta.propietario.id= :usuario";
		Query query = em.createQuery(consulta);
		query.setParameter("usuario",id_usuario);
		return (List<CuentaTotalDTO>) query.getResultList();
	}

	@Override
	public List<CategoriaTotalDTO> getCategoriasConTotal(int id_usuario) {
		String consulta = "SELECT m.categoria.id, m.categoria.nombre, SUM(m.monto) as total FROM Movimiento m WHERE m.cuenta.propietario.id = :usuario GROUP BY m.categoria.id, m.categoria.nombre";
		Query query = em.createQuery(consulta);
		query.setParameter("usuario", id_usuario);
		
		
		return query.getResultList();
	}

    @Override
    public List<Movimiento> getAllByCuenta(int id_cuenta) {
        String consulta = "SELECT m FROM Movimiento m WHERE m.cuenta.id = :idCuenta";
        Query query = em.createQuery(consulta);
        query.setParameter("idCuenta", id_cuenta);
        return query.getResultList();
    }

    @Override
    public List<Movimiento> getAllByCategoria(int id_categoria) {
        String consulta = "SELECT m FROM Movimiento m WHERE m.categoria.id = :idCategoria";
        Query query = em.createQuery(consulta);
        query.setParameter("idCategoria", id_categoria);
        return query.getResultList();
    }

	@Override
	public void ajustarSaldo(Movimiento movimiento) {

	}

	@Override
	public void crearIngreso(Movimiento movimiento) {
		movimiento.setMovimiento(TipoMovimiento.INGRESO);
		em.getTransaction().begin();
		try {
			em.persist(movimiento);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR:JPAGenericDAO:create " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	@Override
	public void crearEgreso(Movimiento movimiento) {
		movimiento.setMovimiento(TipoMovimiento.EGRESO);
		if (movimiento.getMonto()>0){
			movimiento.setMonto(movimiento.getMonto()*-1);
		}
		em.getTransaction().begin();
		try {
			em.persist(movimiento);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR:JPAGenericDAO:create " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	@Override
	public void creaarTransferencia(Movimiento movimientoOrigen, Movimiento movimientoDestino) {
		movimientoOrigen.setMovimiento(TipoMovimiento.TRANSFERENCIA);
		movimientoDestino.setMovimiento(TipoMovimiento.TRANSFERENCIA);
		if (movimientoDestino.getMonto()>0){
			movimientoDestino.setMonto(movimientoDestino.getMonto()*-1);
		}

		em.getTransaction().begin();
		try {
			em.persist(movimientoOrigen);
			em.persist(movimientoDestino);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR:JPAGenericDAO:create " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}


}
