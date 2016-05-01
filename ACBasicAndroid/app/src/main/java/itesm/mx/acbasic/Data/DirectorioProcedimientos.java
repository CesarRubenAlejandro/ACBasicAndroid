package itesm.mx.acbasic.Data;

import java.util.HashMap;

/**
 * Clase que representa la tabla de procedimientos de un programa 
 * @author Cesar, Angela
 *
 */
public class DirectorioProcedimientos {
	// mapa que guarda todos los procedimientos. 
	// la llave String es el nombre del procedimiento
	private HashMap<String, Procedimiento> procedimientos;
	// mapa que guarda las constantes, la llave es el valor de la constante en tipo String
	private HashMap<Integer, Constante> constantes;
	private String nombrePrograma;

	public DirectorioProcedimientos() {
		super();
		procedimientos = new HashMap<String, Procedimiento> ();
		constantes = new HashMap<Integer, Constante> ();
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}



	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}



	public HashMap<String, Procedimiento> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(HashMap<String, Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}



	public HashMap<Integer, Constante> getConstantes() {
		return constantes;
	}

	public void setConstantes(HashMap<Integer, Constante> constantes) {
		this.constantes = constantes;
	}

	/**
	 * Metodo para dar de alta un procedimiento en el directorio de procedimientos
	 * @param procedimientoActual
	 */
	public boolean agregarProcedimiento(Procedimiento procedimientoActual){
		if (this.procedimientos.containsKey(procedimientoActual.getNombreProcedimiento())){
			return false;
		} else {
			this.procedimientos.put(procedimientoActual.getNombreProcedimiento(), procedimientoActual);
			return true;
		}
	}
	
	/**
	 * Metodo para revisar si existe la variable en el procedimiento actual o global
	 * @param nombreProcActual
	 * @param nombreVariable
	 * @return
	 */
	public boolean existeVariable(String nombreProcActual, String nombreVariable){
		return this.procedimientos.get(nombreProcActual).getVariables().containsKey(nombreVariable) ||
				this.procedimientos.get("program").getVariables().containsKey(nombreVariable);
	}

	/**
	 * Metodo para revisar si existe la constante ya declarada en el directorio de procedimientos
	 * @param valorConstante
	 * @return
	 */
	public boolean existeConstante(String valorConstante){
		for (Integer key: this.constantes.keySet()){
			if (this.constantes.get(key).getValorConstante().equals(valorConstante)){
				return true;
			}
		}
		return false;
	}

	public Constante getConstantePorValor(String valorConstante){
		for (Integer key: this.constantes.keySet()){
			if (this.constantes.get(key).getValorConstante().equals(valorConstante)){
				return this.constantes.get(key);
			}
		}
		return null;
	}

	/**
	 * Metodo para obtener una variable si es que existe
	 * @param nombreProcActual
	 * @param nombreVariable
	 * @return
	 */
	public Variable obtenerVariable(String nombreProcActual, String nombreVariable){
		// revisar si el procedimiento tiene una tabla de variables asociada
		if (this.procedimientos.get(nombreProcActual).getVariables() != null){
			if( this.procedimientos.get(nombreProcActual).getVariables().containsKey(nombreVariable)){
				return this.procedimientos.get(nombreProcActual).getVariables().get(nombreVariable);
			} 
		// revisar si el programa tiene variables globales
		}
		if (this.procedimientos.get(this.nombrePrograma).getVariables() != null) {
			if (this.procedimientos.get(this.nombrePrograma).getVariables().containsKey(nombreVariable)){
				return this.procedimientos.get(this.nombrePrograma).getVariables().get(nombreVariable);
			}
		} 
		return null;
	}

	/**
	 * Metodo para regresar el objeto procedimiento basado en su identificador
	 * @param idProcedimiento es el identificador del procedimiento que se desea usar
	 * @return el objeto procedimiento
	 */
	public Procedimiento obtenerProcedimientoPorId(int idProcedimiento){
		for (String key: this.procedimientos.keySet()){
			if (this.procedimientos.get(key).getIdentificadorProcedimiento() == idProcedimiento){
				return this.procedimientos.get(key);
			}
		}
		return null;
	}
	
}
