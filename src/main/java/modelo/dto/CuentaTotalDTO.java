package modelo.dto;

import java.io.Serializable;

public class CuentaTotalDTO implements Serializable {
    private int id;
    private String nombre;
    private double total;

    public CuentaTotalDTO()  {
    }

    public CuentaTotalDTO(int id, String nombre, double total) {
        this.id = id;
        this.nombre = nombre;
        this.total = total;
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

    @Override
    public String toString() {
        return "CuentaTotalDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", total=" + total +
                '}';
    }
}
