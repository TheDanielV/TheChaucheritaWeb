package modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "movimiento")
public class  Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cuenta")
	private Cuenta cuenta;

	@Column(name = "monto")
	private double monto;
	@Column(name = "descripcion")
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Enumerated(EnumType.STRING)
	private TipoMovimiento movimiento;


	
	public Movimiento() {
	}



	public Movimiento(double monto, Date fecha, Cuenta cuenta, Cuenta destino, Categoria categoria) {
		super();

		this.monto = monto;
		this.fecha = fecha;
		this.cuenta = cuenta;
		this.categoria = categoria;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoMovimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(TipoMovimiento movimiento) {
		this.movimiento = movimiento;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", monto=" + monto + ", fecha=" + fecha + ", cuenta"
				+ cuenta + ", destino=" + "]";
	}
	
	
	
}
