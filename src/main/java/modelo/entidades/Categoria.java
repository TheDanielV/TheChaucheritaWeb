package modelo.entidades;

import java.io.Serializable;

public enum Categoria{
    //TODO: categorias predefinidas? o como se manehjaran las categorias
    COMIDA(1, "Comida"), TRANSPORTE(2, "Transporte"), EDUCACION(3,"Educacion"), Personal(4,"Cuidado Personal");
    private int id;
    private  String tipo;

    Categoria() {
    }

    Categoria(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
