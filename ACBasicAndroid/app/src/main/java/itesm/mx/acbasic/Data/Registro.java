package itesm.mx.acbasic.Data;

import java.util.HashMap;

/**
 * Clase que contiene el mapa de memoria para un procedimiento
 */
public class Registro {
    // mapa de memoria
    private HashMap<Integer,String> valores;
    // id del procedimiento al que pertenece
    private int identificadorProcedimiento;
    // direccion de cuadruplo al que debe regresar cuando se termine la ejecucion del procedimiento
    private int direccionRetorno;

    public Registro() {
        valores = new HashMap<Integer,String>();
    }

    public int getIdentificadorProcedimiento() {
        return identificadorProcedimiento;
    }

    public void setIdentificadorProcedimiento(int identificadorProcedimiento) {
        this.identificadorProcedimiento = identificadorProcedimiento;
    }

    public int getDireccionRetorno() {
        return direccionRetorno;
    }

    public void setDireccionRetorno(int direccionRetorno) {
        this.direccionRetorno = direccionRetorno;
    }

    public HashMap<Integer, String> getValores() {
        return valores;
    }

    public void setValores(HashMap<Integer, String> valores) {
        this.valores = valores;
    }

    public void guardaValor (int dirGuardar, String valor) {
        this.valores.put(dirGuardar,valor);
    }

    public String getValor (int dirValor) {
        return this.valores.get(dirValor);
    }
}
