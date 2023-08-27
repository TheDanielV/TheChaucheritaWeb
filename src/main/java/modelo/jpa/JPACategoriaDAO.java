package modelo.jpa;


import modelo.dao.CategoriaDAO;
import modelo.dao.CuentaDAO;
import modelo.entidades.Categoria;
import modelo.entidades.Cuenta;
import modelo.entidades.TipoCategoria;

import javax.persistence.Query;
import java.util.List;


public class JPACategoriaDAO extends JPAGenericDAO<Categoria, Integer> implements CategoriaDAO {

	public JPACategoriaDAO() {
		super(Categoria.class);
	}


	@Override
	public List<Categoria> gellAllByCategoria(TipoCategoria tipoCategoria) {
		String jpql = "SELECT c FROM Categoria c WHERE c.tipo = :tipo";
		return em.createQuery(jpql, Categoria.class)
				.setParameter("tipo", tipoCategoria)
				.getResultList();
	}

	@Override
	public List<Categoria> getAll() {
		String sentence = "SELECT t FROM Categoria t";
		Query query = em.createQuery(sentence);
		List<Categoria> categorias =  query.getResultList();
		if (categorias.isEmpty()){
			create(new Categoria("Comida", TipoCategoria.EGRESO));
			create(new Categoria("Transporte", TipoCategoria.EGRESO));
			create(new Categoria("Nomina", TipoCategoria.INGRESO));
			create(new Categoria("Transferencia",TipoCategoria.TRANSFERENCIA));
			create(new Categoria("Pagos recibidos", TipoCategoria.INGRESO));
			return getAll();
		}else return categorias;

	}
}
