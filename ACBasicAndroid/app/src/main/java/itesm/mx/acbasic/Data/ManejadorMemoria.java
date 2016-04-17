package itesm.mx.acbasic.Data;

public class ManejadorMemoria {

	// variables
	private static int contadorInt = 1000;
	private static int contadorFloat = 2000;
	private static int contadorChar = 3000;
	private static int contadorString = 4000;
	private static int contadorBool = 5000;
	
	//temporales
	private static int contadorTemporalInt = 6000;
	private static int contadorTemporalFloat = 7000;
	private static int contadorTemporalChar = 8000;
	private static int contadorTemporalString = 9000;
	private static int contadorTemporalBool = 10000;
	
	// constantes
	private static int contadorCteInt = 11000;
	private static int contadorCteFloat = 12000;
	private static int contadorCteChar = 13000;
	private static int contadorCteString = 14000;
	private static int contadorCteBool = 15000;
	
	// variables globales
	private static int contadorGlobalInt = 16000;
	private static int contadorGlobalFloat = 17000;
	private static int contadorGlobalChar = 18000;
	private static int contadorGlobalString = 19000;
	private static int contadorGlobalBool = 20000;
	
	public static int getMemoria(int tipoVariable) {
		switch (tipoVariable) {
		case Codigos.INT:
			contadorInt++;
			return contadorInt-1;
		case Codigos.FLOAT:
			contadorFloat++;
			return contadorFloat-1;
		case Codigos.CHAR:
			contadorChar++;
			return contadorChar-1;
		case Codigos.STRING:
			contadorString++;
			return contadorString-1;
		case Codigos.BOOL:
			contadorBool++;
			return contadorBool-1;
		default:
			return 0;
		}	
	}
	
	public static int getMemoriaGlobal(int tipoVariable) {
		switch (tipoVariable) {
		case Codigos.INT:
			contadorGlobalInt++;
			return contadorGlobalInt-1;
		case Codigos.FLOAT:
			contadorGlobalFloat++;
			return contadorGlobalFloat-1;
		case Codigos.CHAR:
			contadorGlobalChar++;
			return contadorGlobalChar-1;
		case Codigos.STRING:
			contadorGlobalString++;
			return contadorGlobalString-1;
		case Codigos.BOOL:
			contadorGlobalBool++;
			return contadorGlobalBool-1;
		default:
			return 0;
		}	
	}
	
	public static int getMemoriaConstante(int tipoVariable) {
		switch (tipoVariable) {
		case Codigos.INT:
			contadorCteInt++;
			return contadorCteInt-1;
		case Codigos.FLOAT:
			contadorCteFloat++;
			return contadorCteFloat-1;
		case Codigos.CHAR:
			contadorCteChar++;
			return contadorCteChar-1;
		case Codigos.STRING:
			contadorCteString++;
			return contadorCteString-1;
		case Codigos.BOOL:
			contadorCteBool++;
			return contadorCteBool-1;
		default:
			return 0;
		}	
	}
	
	public static int getMemoriaTemporal(int tipoVariable) {
		switch (tipoVariable) {
		case Codigos.INT:
			contadorTemporalInt++;
			return contadorTemporalInt-1;
		case Codigos.FLOAT:
			contadorTemporalFloat++;
			return contadorTemporalFloat-1;
		case Codigos.CHAR:
			contadorTemporalChar++;
			return contadorTemporalChar-1;
		case Codigos.STRING:
			contadorTemporalString++;
			return contadorTemporalString-1;
		case Codigos.BOOL:
			contadorTemporalBool++;
			return contadorTemporalBool-1;
		default:
			return 0;
		}	
	}

	public static boolean isInt(int dir){
		return isVarInt(dir) || isTempInt(dir);
	}

	public static boolean isFloat(int dir){
		return isVarFloat(dir) || isTempFloat(dir);
	}

	public static boolean isChar(int dir){
		return isVarChar(dir) || isTempChar(dir);
	}

	public static boolean isString(int dir){
		return isVarString(dir) || isTempString(dir);
	}

	public static boolean isBool(int dir){
		return isVarBool(dir) || isTempBool(dir);
	}

	public static boolean isGlobal(int dir) {
		return (dir >= 16000 && dir < 21000);
	}
	public static boolean isConstante(int dir) {
		return (dir >= 11000 && dir < 16000);
	}
	public static boolean isVarInt (int dir) {
		return ((dir >= 1000 && dir < 2000) || (dir >= 16000 && dir < 17000));
	}
	public static boolean isVarFloat (int dir) {
		return ((dir >= 2000 && dir < 3000) || (dir >= 17000 && dir < 18000));
	}
	public static boolean isVarChar (int dir) {
		return ((dir >= 3000 && dir < 4000) || (dir >= 18000 && dir < 19000));
	}
	public static boolean isVarString (int dir) {
		return ((dir >= 4000 && dir < 5000) || (dir >= 19000 && dir < 20000));
	}
	public static boolean isVarBool (int dir) {
		return ((dir >= 5000 && dir < 6000) || (dir >= 20000 && dir < 21000));
	}
	public static boolean isTempInt (int dir) {
		return (dir >= 6000 && dir < 7000);
	}
	public static boolean isTempFloat (int dir) {
		return (dir >= 7000 && dir < 8000);
	}
	public static boolean isTempChar (int dir) {
		return (dir >= 8000 && dir < 9000);
	}
	public static boolean isTempString (int dir) {
		return (dir >= 9000 && dir < 10000);
	}
	public static boolean isTempBool (int dir) {
		return (dir >= 10000 && dir < 11000);
	}
}
