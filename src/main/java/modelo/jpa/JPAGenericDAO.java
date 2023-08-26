package modelo.jpa;

import modelo.dao.GenericDAO;
import modelo.entidades.Cuenta;

import javax.persistence.*;
import java.util.List;

public class JPAGenericDAO<T, ID> implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	protected EntityManager em;

	public JPAGenericDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		this.em = Persistence.createEntityManagerFactory("ProyectoWeb").createEntityManager();
	}

	@Override
	public T getById(ID id) {
		return em.find(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		String sentence = "SELECT t FROM " + persistentClass.getName() + " t";
		Query query = em.createQuery(sentence);
		return query.getResultList();
	}

	@Override
	public void create(T entity) {
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("No se ha realizado JPAGenericDAO.create - Error: " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("No se ha realizado JPAGenericDAO.update - Error: " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		try {
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("No se ha realizado JPAGenericDAO.delete - Error: " + e);
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
		}

	}

	@Override
	public void deleteByID(ID id) {
		T entity = this.getById(id);
		if (entity != null)
			this.delete(entity);

	}


}
