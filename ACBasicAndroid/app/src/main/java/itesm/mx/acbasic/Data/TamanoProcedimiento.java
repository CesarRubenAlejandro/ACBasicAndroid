package itesm.mx.acbasic.Data;

public class TamanoProcedimiento {

	private int cantidadVarInt;
	private int cantidadVarFloat;
	private int cantidadVarChar;
	private int cantidadVarString;
	private int cantidadVarBool;
	
	private int cantidadTempInt;
	private int cantidadTempFloat;
	private int cantidadTempChar;
	private int cantidadTempString;
	private int cantidadTempBool;
	
	public TamanoProcedimiento() {
		super();
		cantidadVarInt = 0;
		cantidadVarFloat = 0;
		cantidadVarChar = 0;
		cantidadVarString = 0;
		cantidadVarBool = 0;
		cantidadTempInt = 0;
		cantidadTempFloat = 0;
		cantidadTempChar = 0;
		cantidadTempString = 0;
		cantidadTempBool = 0;
	}

	public int getCantidadVarInt() {
		return cantidadVarInt;
	}

	public void setCantidadVarInt(int cantidadVarInt) {
		this.cantidadVarInt = cantidadVarInt;
	}

	public int getCantidadVarFloat() {
		return cantidadVarFloat;
	}

	public void setCantidadVarFloat(int cantidadVarFloat) {
		this.cantidadVarFloat = cantidadVarFloat;
	}

	public int getCantidadVarChar() {
		return cantidadVarChar;
	}

	public void setCantidadVarChar(int cantidadVarChar) {
		this.cantidadVarChar = cantidadVarChar;
	}

	public int getCantidadVarString() {
		return cantidadVarString;
	}

	public void setCantidadVarString(int cantidadVarString) {
		this.cantidadVarString = cantidadVarString;
	}

	public int getCantidadVarBool() {
		return cantidadVarBool;
	}

	public void setCantidadVarBool(int cantidadVarBool) {
		this.cantidadVarBool = cantidadVarBool;
	}

	public int getCantidadTempInt() {
		return cantidadTempInt;
	}

	public void setCantidadTempInt(int cantidadTempInt) {
		this.cantidadTempInt = cantidadTempInt;
	}

	public int getCantidadTempFloat() {
		return cantidadTempFloat;
	}

	public void setCantidadTempFloat(int cantidadTempFloat) {
		this.cantidadTempFloat = cantidadTempFloat;
	}

	public int getCantidadTempChar() {
		return cantidadTempChar;
	}

	public void setCantidadTempChar(int cantidadTempChar) {
		this.cantidadTempChar = cantidadTempChar;
	}

	public int getCantidadTempString() {
		return cantidadTempString;
	}

	public void setCantidadTempString(int cantidadTempString) {
		this.cantidadTempString = cantidadTempString;
	}

	public int getCantidadTempBool() {
		return cantidadTempBool;
	}

	public void setCantidadTempBool(int cantidadTempBool) {
		this.cantidadTempBool = cantidadTempBool;
	}
	
	public void setTamanoVar(int tipo) {
		switch (tipo) {
			case Codigos.INT:
				cantidadVarInt++;
				break;
			case Codigos.FLOAT:
				cantidadVarFloat++;
				break;
			case Codigos.CHAR:
				cantidadVarChar++;
				break;
			case Codigos.STRING:
				cantidadVarString++;
				break;
			case Codigos.BOOL:
				cantidadVarBool++;
				break;
		}
	}
	
	public void setTamanoTemp(int tipo) {
		switch (tipo) {
			case Codigos.INT:
				cantidadTempInt++;
				break;
			case Codigos.FLOAT:
				cantidadTempFloat++;
				break;
			case Codigos.CHAR:
				cantidadTempChar++;
				break;
			case Codigos.STRING:
				cantidadTempString++;
				break;
			case Codigos.BOOL:
				cantidadTempBool++;
				break;
		}
	}
}