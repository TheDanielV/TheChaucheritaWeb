package modelo.jpa;


import modelo.dao.CuentaDAO;
import modelo.dao.DAOFactory;
import modelo.entidades.Cuenta;
import modelo.entidades.Movimiento;
import modelo.entidades.TipoCategoria;
import modelo.entidades.Usuario;

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
	public List<Cuenta> getAllByID(Usuario propietario) {
		Query query = em.createQuery("SELECT c FROM Cuenta c WHERE c.propietario = :propietario", Cuenta.class);
		query.setParameter("propietario", propietario);
		return query.getResultList();
	}

	@Override
	public void deleteByID(Integer integer) {

			for (Movimiento movimineto : DAOFactory.getFactory().getMovimientoDAO().getAllByCuenta(integer)
			) {
				DAOFactory.getFactory().getMovimientoDAO().deleteByID(movimineto.getId());
			}
				delete(getById(integer));


	}
}
