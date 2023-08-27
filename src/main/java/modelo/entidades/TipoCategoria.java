package modelo.entidades;

public enum TipoCategoria {
	INGRESO ("INGRESO", 1),
	EGRESO ("EGRESO", 2),
	TRANSFERENCIA ("TRANSFERENCIA",3);
	
	private String nombreTipo;
	private int idTipo;

	TipoCategoria() {
	}

	TipoCategoria(String nombreTipo, int id) {
		this.idTipo = id;
		this.nombreTipo = nombreTipo; 
	}
	
	public String getNombreTipo() {
		return nombreTipo;
	}
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

}
