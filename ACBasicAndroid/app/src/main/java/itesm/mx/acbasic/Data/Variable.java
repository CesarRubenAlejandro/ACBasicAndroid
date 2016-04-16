package itesm.mx.acbasic.Data;

/**
 * Clase que representa una variable del lenguaje ACBasic
 * @author Cesar, Angela
 *
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
	
	public Variable() {
		super();
	}

	public Variable(String nombreVariable, int tipoVariable, String scope) {
		super();
		this.nombreVariable = nombreVariable;
		this.tipoVariable = tipoVariable;
		this.scope = scope;
		if (this.scope.equals("global")){
			this.direccionVariable = ManejadorMemoria.getMemoriaGlobal(tipoVariable);
		} else {
			this.direccionVariable = ManejadorMemoria.getMemoria(tipoVariable);
		}
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
	
}
