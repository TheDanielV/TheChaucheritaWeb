package modelo.jpa;

import modelo.dao.MovimientoDAO;
import modelo.dto.CuentaTotalDTO;
import modelo.entidades.Movimiento;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("ALL")
public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento, Integer> implements MovimientoDAO {

	public JPAMovimientoDAO() {
		super(Movimiento.class);
	}


	@Override
	public List<CuentaTotalDTO> getCuentasConTotal(int id_usuario) {
		String consulta = "SELECT m.cuenta.id, m.cuenta.nombre, sum(m.monto) as 'total' from movimiento m where m.cuenta.propietario.id= :usuario";
		Query query = em.createQuery(consulta);
		query.setParameter('usuario',id_usuario);
		return (List<CuentaTotalDTO>) query.getResultList();
	}

	@Override
	public List<CategoriaTotalDTO> getCategoriasConTotal(int id_usuario) {
		return null;
	}
}
