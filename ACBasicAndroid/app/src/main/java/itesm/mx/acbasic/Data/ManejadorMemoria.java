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
}
