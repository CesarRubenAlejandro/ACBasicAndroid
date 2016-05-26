package itesm.mx.acbasic.Data;

/**
 * Clase que representa una variable del lenguaje ACBasic
 */
public class Variable {
	// nombre de la variable (ID)
	private String nombreVariable;
	// define el tipo de la variable (int, float, bool, ...)
	private int tipoVariable;
	// define el alcance de la variable (global, local)
	private String scope;
	//define la direccion logico 
	private int direccionVariable;
	//tamaño si es un arreglo
	private int sizeVariable;

	public Variable() {
		super();
		this.sizeVariable = 0;
	}

	/**
	 * Metodo constructor
	 * @param nombreVariable es el id de la variable
	 * @param tipoVariable es el tipo de la variable
	 * @param scope puede ser local o global
	 */
	public Variable(String nombreVariable, int tipoVariable, String scope) {
		super();
		this.nombreVariable = nombreVariable;
		this.tipoVariable = tipoVariable;
		this.scope = scope;
		// en caso que la variable sea global, se le asigna una direccion de tipo global
		if (this.scope.equals("global")){
			this.direccionVariable = ManejadorMemoria.getMemoriaGlobal(tipoVariable);
		} else {
			this.direccionVariable = ManejadorMemoria.getMemoria(tipoVariable);
		}
		// por default todas las variables tienen size 0
		this.sizeVariable = 0;
	}

	public String getNombreVariable() {
		return nombreVariable;
	}
	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	public int getTipoVariable() {
		return tipoVariable;
	}
	public void setTipoVariable(int tipoVariable) {
		setDireccionVariable(ManejadorMemoria.getMemoria(tipoVariable));
		this.tipoVariable = tipoVariable;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	public int getDireccionVariable() {
		return direccionVariable;
	}

	public void setDireccionVariable(int direccionVariable) {
		this.direccionVariable = direccionVariable;
	}

	public int getSizeVariable() {
		return sizeVariable;
	}

	/**
	 * Metodo set para el atributo sizeVariable
	 * @param sizeVariable es el tamano de la variable
	 */
	public void setSizeVariable(int sizeVariable) {
		this.sizeVariable = sizeVariable;
		// reservar memoria para las cada casilla del arreglo
		ManejadorMemoria.separaMemoriaArreglo(this.tipoVariable, this.sizeVariable, this.direccionVariable);
	}

}
