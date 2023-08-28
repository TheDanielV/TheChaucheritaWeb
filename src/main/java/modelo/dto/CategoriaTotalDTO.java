package modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class CategoriaTotalDTO implements Serializable {
    private int id;
    private String nombre;
    private double total;
    private Date mes;

    public CategoriaTotalDTO()  {
    }

    public CategoriaTotalDTO(int id, String nombre, double total, Date mes) {
        this.id = id;
        this.nombre = nombre;
        this.total = total;
        this.mes = mes;
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

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "CategoriaTotalDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", total=" + total +
                ", mes='" + mes + '\'' +
                '}';
    }
}
