package itesm.mx.acbasic.Data;

import java.util.HashMap;

/**
 * Created by Cesar on 4/16/2016.
 */
public class Registro {
    private HashMap<Integer, Integer> variablesInt;
    private HashMap<Integer, Double> variablesFloat;
    private HashMap<Integer, Character> variablesChar;
    private HashMap<Integer, String> variablesString;
    private HashMap<Integer, Boolean> variablesBool;

    private HashMap<Integer, Integer> temporalesInt;
    private HashMap<Integer, Double> temporalesFloat;
    private HashMap<Integer, Character> temporalesChar;
    private HashMap<Integer, String> temporalesString;
    private HashMap<Integer, Boolean> temporalesBool;

    public Registro() {
        variablesInt = new HashMap<Integer,Integer>();
        variablesFloat = new HashMap<Integer,Double>();
        variablesChar = new HashMap<Integer,Character>();
        variablesString = new HashMap<Integer,String>();
        variablesBool = new HashMap<Integer,Boolean>();

        temporalesInt = new HashMap<Integer,Integer>();
        temporalesFloat = new HashMap<Integer,Double>();
        temporalesChar = new HashMap<Integer,Character>();
        temporalesString = new HashMap<Integer,String>();
        temporalesBool = new HashMap<Integer,Boolean>();
    }

    public HashMap<Integer, Integer> getVariablesInt() {
        return variablesInt;
    }

    public void setVariablesInt(HashMap<Integer, Integer> variablesInt) {
        this.variablesInt = variablesInt;
    }

    public HashMap<Integer, Double> getVariablesFloat() {
        return variablesFloat;
    }

    public void setVariablesFloat(HashMap<Integer, Double> variablesFloat) {
        this.variablesFloat = variablesFloat;
    }

    public HashMap<Integer, Character> getVariablesChar() {
        return variablesChar;
    }

    public void setVariablesChar(HashMap<Integer, Character> variablesChar) {
        this.variablesChar = variablesChar;
    }

    public HashMap<Integer, String> getVariablesString() {
        return variablesString;
    }

    public void setVariablesString(HashMap<Integer, String> variablesString) {
        this.variablesString = variablesString;
    }

    public HashMap<Integer, Boolean> getVariablesBool() {
        return variablesBool;
    }

    public void setVariablesBool(HashMap<Integer, Boolean> variablesBool) {
        this.variablesBool = variablesBool;
    }

    public HashMap<Integer, Integer> getTemporalesInt() {
        return temporalesInt;
    }

    public void setTemporalesInt(HashMap<Integer, Integer> temporalesInt) {
        this.temporalesInt = temporalesInt;
    }

    public HashMap<Integer, Double> getTemporalesFloat() {
        return temporalesFloat;
    }

    public void setTemporalesFloat(HashMap<Integer, Double> temporalesFloat) {
        this.temporalesFloat = temporalesFloat;
    }

    public HashMap<Integer, Character> getTemporalesChar() {
        return temporalesChar;
    }

    public void setTemporalesChar(HashMap<Integer, Character> temporalesChar) {
        this.temporalesChar = temporalesChar;
    }

    public HashMap<Integer, String> getTemporalesString() {
        return temporalesString;
    }

    public void setTemporalesString(HashMap<Integer, String> temporalesString) {
        this.temporalesString = temporalesString;
    }

    public HashMap<Integer, Boolean> getTemporalesBool() {
        return temporalesBool;
    }

    public void setTemporalesBool(HashMap<Integer, Boolean> temporalesBool) {
        this.temporalesBool = temporalesBool;
    }

    public void guardaValor (int dirGuardar, String valor) {
        if(ManejadorMemoria.isVarInt(dirGuardar)) {
            this.variablesInt.put(dirGuardar, Integer.parseInt(valor));

        } else if(ManejadorMemoria.isVarFloat(dirGuardar)) {
            this.variablesFloat.put(dirGuardar, Double.parseDouble(valor));

        } else if(ManejadorMemoria.isVarChar(dirGuardar)) {
            this.variablesChar.put(dirGuardar, valor.charAt(0));

        } else if(ManejadorMemoria.isVarString(dirGuardar)) {
            this.variablesString.put(dirGuardar, valor);

        } else if(ManejadorMemoria.isVarBool(dirGuardar)) {
            this.variablesBool.put(dirGuardar, Boolean.parseBoolean(valor));

        } else if(ManejadorMemoria.isTempInt(dirGuardar)) {
            this.temporalesInt.put(dirGuardar, Integer.parseInt(valor));

        } else if(ManejadorMemoria.isTempFloat(dirGuardar)) {
            this.temporalesFloat.put(dirGuardar, Double.parseDouble(valor));

        } else if(ManejadorMemoria.isTempChar(dirGuardar)) {
            this.temporalesChar.put(dirGuardar, valor.charAt(0));

        } else if(ManejadorMemoria.isTempString(dirGuardar)) {
            this.temporalesString.put(dirGuardar, valor);

        } else if(ManejadorMemoria.isTempBool(dirGuardar)) {
            this.temporalesBool.put(dirGuardar, Boolean.parseBoolean(valor));
        }
    }
}
