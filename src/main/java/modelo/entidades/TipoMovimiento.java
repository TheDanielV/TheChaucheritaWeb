package modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public enum TipoMovimiento implements Serializable {

    INGRESO ("INGRESO", 1),
    EGRESO("EGRESO", 2),
    TRANSFERENCIA ("TRANSFERENCIA",3);



    private  int idTipo;

    private String nombreTipo;

    TipoMovimiento() {
    }

    TipoMovimiento(String nombreTipo, int id) {
        this.idTipo = id;
        this.nombreTipo = nombreTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public String toString() {
        return "TipoMovimiento{" +
                "idTipo=" + idTipo +
                ", nombreTipo='" + nombreTipo + '\'' +
                '}';
    }
}
