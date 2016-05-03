package itesm.mx.acbasic.Data;

/**
 * Clase que contiene todas las posibles combinaciones semanticas del lenguaje
 */
public class CuboSemantico {
	private int[][][] cubo;

	public CuboSemantico(){
		cubo = new int [5][5][7];
		// llenado del cubo semantico
		// INT izquiedo
		// int con int
		cubo[Codigos.INT][Codigos.INT][Codigos.SUMA] = Codigos.INT;
		cubo[Codigos.INT][Codigos.INT][Codigos.RESTA] = Codigos.INT;
		cubo[Codigos.INT][Codigos.INT][Codigos.DIV] = Codigos.INT;
		cubo[Codigos.INT][Codigos.INT][Codigos.MULT] = Codigos.INT;
		cubo[Codigos.INT][Codigos.INT][Codigos.ASSIGN] = Codigos.INT;
		cubo[Codigos.INT][Codigos.INT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.INT][Codigos.OPREL] = Codigos.BOOL;
		
		// int con float
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.SUMA] = Codigos.FLOAT;
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.RESTA] = Codigos.FLOAT;
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.DIV] = Codigos.FLOAT;
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.MULT] = Codigos.FLOAT;
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.ASSIGN] = Codigos.INT;
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.FLOAT][Codigos.OPREL] = Codigos.BOOL;
		
		// int con char
		cubo[Codigos.INT][Codigos.CHAR][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.CHAR][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.CHAR][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.CHAR][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.CHAR][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.CHAR][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.CHAR][Codigos.OPREL] = Codigos.ERROR;
		
		// int con string
		cubo[Codigos.INT][Codigos.STRING][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.STRING][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.STRING][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.STRING][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.STRING][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.STRING][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.STRING][Codigos.OPREL] = Codigos.ERROR;
		
		// int con bool
		cubo[Codigos.INT][Codigos.BOOL][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.BOOL][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.BOOL][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.BOOL][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.BOOL][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.BOOL][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.INT][Codigos.BOOL][Codigos.OPREL] = Codigos.ERROR;
		
		// FLOAT izquierdo
		// float con int
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.SUMA] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.RESTA] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.DIV] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.MULT] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.ASSIGN] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.INT][Codigos.OPREL] = Codigos.BOOL;
		
		// float con float
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.SUMA] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.RESTA] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.DIV] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.MULT] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.ASSIGN] = Codigos.FLOAT;
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.FLOAT][Codigos.OPREL] = Codigos.BOOL;
		
		// float con char
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.CHAR][Codigos.OPREL] = Codigos.ERROR;
		
		// float con string
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.STRING][Codigos.OPREL] = Codigos.ERROR;
		
		// float con bool
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.FLOAT][Codigos.BOOL][Codigos.OPREL] = Codigos.ERROR;
		
		// CHAR lado izquerdo
		// char con int
		cubo[Codigos.CHAR][Codigos.INT][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.INT][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.INT][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.INT][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.INT][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.INT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.INT][Codigos.OPREL] = Codigos.ERROR;
		
		// char con float
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.FLOAT][Codigos.OPREL] = Codigos.ERROR;
		
		// char con char
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.ASSIGN] = Codigos.CHAR;
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.CHAR][Codigos.OPREL] = Codigos.ERROR;
		
		// char con string
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.STRING][Codigos.OPREL] = Codigos.ERROR;
		
		// char con bool
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.CHAR][Codigos.BOOL][Codigos.OPREL] = Codigos.ERROR;
		
		// STRING lado izquierdo
		// string con int
		cubo[Codigos.STRING][Codigos.INT][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.INT][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.INT][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.INT][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.INT][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.INT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.INT][Codigos.OPREL] = Codigos.ERROR;
		
		// string con float
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.FLOAT][Codigos.OPREL] = Codigos.ERROR;
		
		// string con char
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.CHAR][Codigos.OPREL] = Codigos.ERROR;
		
		// string con string
		cubo[Codigos.STRING][Codigos.STRING][Codigos.SUMA] = Codigos.STRING;
		cubo[Codigos.STRING][Codigos.STRING][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.STRING][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.STRING][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.STRING][Codigos.ASSIGN] = Codigos.STRING;
		cubo[Codigos.STRING][Codigos.STRING][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.STRING][Codigos.OPREL] = Codigos.ERROR;
		
		// string con bool
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.STRING][Codigos.BOOL][Codigos.OPREL] = Codigos.ERROR;
		
		// BOOL lado izquierdo
		// bool con int
		cubo[Codigos.BOOL][Codigos.INT][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.INT][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.INT][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.INT][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.INT][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.INT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.INT][Codigos.OPREL] = Codigos.ERROR;
		
		// bool con float
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.FLOAT][Codigos.OPREL] = Codigos.ERROR;
		
		// bool con char
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.CHAR][Codigos.OPREL] = Codigos.ERROR;
		
		// bool con string
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.ASSIGN] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.OPLOGIC] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.STRING][Codigos.OPREL] = Codigos.ERROR;
		
		// bool con bool
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.SUMA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.RESTA] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.DIV] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.MULT] = Codigos.ERROR;
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.ASSIGN] = Codigos.BOOL;
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.OPLOGIC] = Codigos.BOOL;
		cubo[Codigos.BOOL][Codigos.BOOL][Codigos.OPREL] = Codigos.ERROR;
	}
	
	public int[][][] getCubo() {
		return cubo;
	}

	public void setCubo(int[][][] cubo) {
		this.cubo = cubo;
	}
	
}
