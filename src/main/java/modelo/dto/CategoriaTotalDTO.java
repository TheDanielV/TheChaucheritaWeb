package modelo.dto;

import java.io.Serializable;

public class CategoriaTotalDTO implements Serializable {
    private int id;
    private String nombre;
    private double total;

    public CategoriaTotalDTO()  {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
