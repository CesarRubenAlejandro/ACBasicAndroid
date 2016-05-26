package itesm.mx.acbasic.Data;

/**
 * Clase que representa una constante del lenguaje ACBasic
 */
public class Constante {
	// tipo de dato de la constante
	private int tipoConstante;
	// direccion virtual de la constante
	private int direccionConstante;
	// valor en string de la constante
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
