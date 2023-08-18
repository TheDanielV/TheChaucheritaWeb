package modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	
	public Usuario() {
		
	}

	public Usuario(String nombre, String clave) {
		super();
		this.username = nombre;
		this.password = clave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return username;
	}

	public void setNombre(String nombre) {
		this.username = nombre;
	}

	public String getClave() {
		return password;
	}

	public void setClave(String clave) {
		this.password = clave;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + username + ", contrasenia=" + password + "]";
	}
	
	
}


