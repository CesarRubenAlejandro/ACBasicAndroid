package itesm.mx.acbasic.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que representa un procedimiento (func) del lenguaje ACBasic
 */
public class Procedimiento {
	// nombre (id) del procedimiento
	private String nombreProcedimiento;
	// tipo (void, int, float, bool...) del procedimiento
	private int tipoProcedimiento;
	// mapa de variables declaradas dentro de este procedimiento en el programa
	// la llave String es el id de la variable
	private HashMap<String, Variable> variables;
	// numero de cuadruplo inicial
	private int cuadruploInicial;
	// tipos de los parametros
	private ArrayList <Integer> tipoParams;

	// DIRECCION(es) VIRTUAL(es) de los PARAMETROS
	private ArrayList<Integer> direccionParametros;
	// indicadores para saber si algun parametro es por referencia
	private ArrayList<Boolean> indicadorPorReferencia;
	// fila de listas de direcciones de todas las llamadas hechas a la funcion
	private Queue<ArrayList<Integer>> filaDireccionesLlamada;
	// identificador del procedimiento
	private int identificadorProcedimiento;


	public Procedimiento() {
		super();
		tipoParams = new ArrayList<Integer>();
		direccionParametros = new ArrayList<Integer>();
		indicadorPorReferencia = new ArrayList<Boolean>();
		filaDireccionesLlamada = new LinkedList<ArrayList<Integer>>();
	}


	public Queue<ArrayList<Integer>> getFilaDireccionesLlamada() {
		return filaDireccionesLlamada;
	}


	public void setFilaDireccionesLlamada(Queue<ArrayList<Integer>> filaDireccionesLlamada) {
		this.filaDireccionesLlamada = filaDireccionesLlamada;
	}

	public ArrayList<Boolean> getIndicadorPorReferencia() {
		return indicadorPorReferencia;
	}

	public void setIndicadorPorReferencia(ArrayList<Boolean> indicadorPorReferencia) {
		this.indicadorPorReferencia = indicadorPorReferencia;
	}

	public ArrayList<Integer> getDireccionParametros() {
		return direccionParametros;
	}

	public void setDireccionParametros(ArrayList<Integer> direccionParametros) {
		this.direccionParametros = direccionParametros;
	}

	public int getIdentificadorProcedimiento() {
		return identificadorProcedimiento;
	}

	public void setIdentificadorProcedimiento(int identificadorProcedimiento) {
		this.identificadorProcedimiento = identificadorProcedimiento;
	}

	public String getNombreProcedimiento() {
		return nombreProcedimiento;
	}

	public void setNombreProcedimiento(String nombreProcedimiento) {
		this.nombreProcedimiento = nombreProcedimiento;
	}

	public int getTipoProcedimiento() {
		return tipoProcedimiento;
	}

	public void setTipoProcedimiento(int tipoProcedimiento) {
		this.tipoProcedimiento = tipoProcedimiento;
	}

	public HashMap<String, Variable> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, Variable> variables) {
		this.variables = variables;
	}

	public int getCuadruploInicial() {
		return cuadruploInicial;
	}

	public void setCuadruploInicial(int cuadruploInicial) {
		this.cuadruploInicial = cuadruploInicial;
	}

	public ArrayList<Integer> getTipoParams() {
		return tipoParams;
	}

	public void setTipoParams(ArrayList<Integer> tipoParams) {
		this.tipoParams = tipoParams;
	}

	/**
	 * Metodo para crear la tabla de variables del procedimiento actual
	 */
	public void crearTablaDeVariables(){
		this.variables = new HashMap<String, Variable>();
	}

	/**
	 * Metodo para dar de alta una variable en el directorio de variables del procedimiento actual
	 * @param variableActual
	 */
	public boolean agregarVariable(Variable variableActual){
		if (this.variables.containsKey(variableActual.getNombreVariable())){
			return false;
		} else {
			this.variables.put(variableActual.getNombreVariable(), variableActual);
			return true;
		}
	}

	/**
	 * Método para obtener la cantidad de parametros
	 */
	public int getCantidadParametros() {
		return this.tipoParams.size();
	}


	/**
	 * Metodo para comparar los parametros de una llamada con los parametros
	 * esperados en la funcion
	 * @param paramLlamada es una lista con los tipos de los parametros en la funcion
	 */
	public boolean comparaParams(ArrayList <Integer> paramLlamada) {
		// los parametros enviados deben ser el mismo numero que los que declaro la funcion
		if(paramLlamada.size() != this.getCantidadParametros()) {
			return false;
		}
		// los tipos de los parametros deben coincidir con los declarados
		for(int i=0; i<paramLlamada.size(); i++){
			if (paramLlamada.get(i)!= this.tipoParams.get(i)){
				return false;
			}
		}
		return true;
	}
}