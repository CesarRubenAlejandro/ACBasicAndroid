package itesm.mx.acbasic.Data;

import java.util.HashMap;

/**
 * Created by Cesar on 4/16/2016.
 */
public class Registro {
    private HashMap<Integer,String> valores;
    private int identificadorProcedimiento;
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
