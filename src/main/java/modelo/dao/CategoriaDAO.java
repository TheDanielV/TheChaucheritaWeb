package modelo.dao;

import modelo.entidades.Categoria;
import modelo.entidades.Cuenta;
import modelo.entidades.TipoCategoria;

import java.util.List;

public interface CategoriaDAO extends GenericDAO<Categoria, Integer>{
    public List<Categoria> gellAllByCategoria(TipoCategoria tipoCategoria);
    public Categoria getCategoriaTransferencia();
}
