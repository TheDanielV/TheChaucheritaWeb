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
		String sentencia = "SELECT p FROM Usuario p WHERE p.username= :nombre AND p.password= :clave";

		Query query = em.createQuery(sentencia);
		// Parametros ....
		query.setParameter("nombre", nombre);
		query.setParameter("clave", clave);

		Usuario personaAutorizada = (Usuario) query.getSingleResult();
		return personaAutorizada;
	}
	

}
