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
		
		Usuario usuarioAutorizado = null;

		try {
			usuarioAutorizado = (Usuario) query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			usuarioAutorizado = null;
			System.out.println("El usuario no existe en la base de datos. Error: " + e);
		}
		return usuarioAutorizado;
	}

	@Override
	public Usuario validarUsuarioParaRegistrar(String nombre, String clave) {
		// First, let's see if the user already exists
		String sentencia = "SELECT u FROM Usuario u WHERE u.nombre= :nombre";

		Query query = em.createQuery(sentencia);
		query.setParameter("nombre", nombre);

		Usuario usuarioExistente = null;
		try {
			usuarioExistente = (Usuario) query.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Usuario no encontrado en la tabla. Listo para ingresar nuevo usuario");
		}

		if (usuarioExistente != null) {
			return null;
		}

		Usuario nuevoUsuario = new Usuario(nombre, clave);
		this.create(nuevoUsuario);

		return nuevoUsuario;
	}

}
