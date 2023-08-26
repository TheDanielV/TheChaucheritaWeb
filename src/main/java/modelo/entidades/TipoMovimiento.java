package modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public enum TipoMovimiento implements Serializable {

    INGRESO ("INGRESO", 1),
    EGRESO("EGRESO", 2),
    TRANSFERENCIA ("TRANSFERENCIA",3);


    @Id
    private  int idTipo;
    @Column(name = "tipo")
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
}
