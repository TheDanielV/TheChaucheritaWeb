package modelo.jpa;

import modelo.dao.CuentaDAO;
import modelo.dao.DAOFactory;
import modelo.dto.CuentaTotalDTO;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;
import modelo.entidades.TipoCategoria;
import modelo.entidades.Usuario;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

public class JPACuentaDAO extends JPAGenericDAO<Cuenta, Integer> implements CuentaDAO {

	public JPACuentaDAO() {
		super(Cuenta.class);
	}

	@Override
	public Double getTotalCuenta(int id_usuario, int id_cuenta) {
		String consulta = "SELECT SUM(m.monto) " +
				"FROM Movimiento m " +
				"JOIN m.cuenta c " +
				"WHERE c.propietario.id = :usuario AND c.id = :cuenta";

		TypedQuery<Double> query = em.createQuery(consulta, Double.class);
		query.setParameter("usuario", id_usuario);
		query.setParameter("cuenta", id_cuenta);

		Double total = query.getSingleResult();

		// En caso de que no haya movimientos y la suma sea nula, devolvemos 0.0.
		return (total != null) ? total : 0.0;
	}

	@Override
	public List<Cuenta> getAllByID(Usuario propietario) {
		Query query = em.createQuery("SELECT c FROM Cuenta c WHERE c.propietario = :propietario", Cuenta.class);
		query.setParameter("propietario", propietario);
		return query.getResultList();
	}

	@Override
	public void deleteByID(Integer integer) {

		for (Movimiento movimineto : DAOFactory.getFactory().getMovimientoDAO().getAllByCuenta(integer)) {
			DAOFactory.getFactory().getMovimientoDAO().deleteByID(movimineto.getId());
		}
		delete(getById(integer));

	}
}
