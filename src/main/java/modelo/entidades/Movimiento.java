package modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	
	@Column(name = "valor")
	private double valor;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta_origen")
	private Cuenta origen;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta_destino")
	private Cuenta destino;
	
	
	public Movimiento() {
	}



	public Movimiento(double valor, Date fecha, Cuenta origen, Cuenta destino) {
		super();

		this.valor = valor;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public double getValor() {
		return valor;
	}



	public void setValor(double valor) {
		this.valor = valor;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Cuenta getOrigen() {
		return origen;
	}



	public void setOrigen(Cuenta origen) {
		this.origen = origen;
	}



	public Cuenta getDestino() {
		return destino;
	}



	public void setDestino(Cuenta destino) {
		this.destino = destino;
	}



	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", valor=" + valor + ", fecha=" + fecha + ", origen="
				+ origen + ", destino=" + destino + "]";
	}
	
	
	
}
