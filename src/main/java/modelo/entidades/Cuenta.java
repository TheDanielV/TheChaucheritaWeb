package modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cuenta implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="propietario")
	private Usuario propietario;


	@Column(name = "nombre")
	private String nombre;


	
	public Cuenta() {}

	public Cuenta(  Usuario propetario) {
		super();
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


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cuenta{" +
				"id=" + id +
				", propietario=" + propietario +
				", nombre='" + nombre + '\'' +
				'}';
	}
}
