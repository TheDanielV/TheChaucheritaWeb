package modelo.jpa;


import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;

import javax.persistence.Query;

public class JPAUsuarioDAO extends JPAGenericDAO<Usuario, Integer> implements UsuarioDAO {

	public JPAUsuarioDAO() {
		super(Usuario.class);
	}
	@Override
	public Usuario autorizar(String nombre, String clave) {
		String sentencia = "SELECT u FROM Usuario u WHERE u.nombre= :nombre AND u.clave= :clave";

		Query query = em.createQuery(sentencia);
		query.setParameter("nombre", nombre);
		query.setParameter("clave", clave);

		Usuario usuarioAutorizado = (Usuario) query.getSingleResult();
		return usuarioAutorizado;
	}

	@Override
	public Usuario validarUsuarioParaRegistrar(String nombre, String clave) {
		throw new UnsupportedOperationException("Unimplemented method 'validarUsuarioParaRegistrar'");
	}
	

}
