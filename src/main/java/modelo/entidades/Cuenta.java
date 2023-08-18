package modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cuenta implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="propietario")
	private Usuario propietario;
	
	@Column
	private double monto;
	@Enumerated
	private CuentaTipo tipo;

	
	public Cuenta() {}

	public Cuenta( double total, Usuario propetario) {
		super();
		this.monto = total;
		this.propietario = propetario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public double getTotal() {
		return monto;
	}

	public void setTotal(double total) {
		this.monto = total;
	}

	
	public CuentaTipo getTipo() {
		return tipo;
	}

	public void setTipo(CuentaTipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", total=" + monto + "]";
	}
	
	
}
