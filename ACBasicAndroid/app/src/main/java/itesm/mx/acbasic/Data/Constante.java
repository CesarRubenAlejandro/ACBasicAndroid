package itesm.mx.acbasic.Data;

public class Constante {
	private int tipoConstante;
	private int direccionConstante;
	private String valorConstante;
	
	public Constante(int tipoConstante, int direccionConstante, String valorConstante) {
		super();
		this.tipoConstante = tipoConstante;
		this.direccionConstante = direccionConstante;
		this.valorConstante = valorConstante;
	}

	public int getTipoConstante() {
		return tipoConstante;
	}

	public void setTipoConstante(int tipoConstante) {
		this.tipoConstante = tipoConstante;
	}

	public int getDireccionConstante() {
		return direccionConstante;
	}

	public void setDireccionConstante(int direccionConstante) {
		this.direccionConstante = direccionConstante;
	}

	public String getValorConstante() {
		return valorConstante;
	}

	public void setValorConstante(String valorConstante) {
		this.valorConstante = valorConstante;
	}
}
