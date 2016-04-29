package itesm.mx.acbasic.Data;

import android.util.MalformedJsonException;

import java.util.Stack;

/**
 * Created by Cesar, Angela on 4/16/2016.
 */
public class MaquinaVirtual {
    private DirectorioProcedimientos directorioProcedimientos;
    private Registro registroGlobal;
    private Stack<Registro> pilaRegistros;
    private String valorRetorno;

    public MaquinaVirtual(DirectorioProcedimientos directorioProcedimientos) {
        this.directorioProcedimientos = directorioProcedimientos;
        registroGlobal = new Registro();
        pilaRegistros = new Stack<Registro>();
        valorRetorno = "";

        // SOLO PARA PROBAR
        pilaRegistros.push(new Registro()); // registro main
    }

    /**
     * Metodo para traducir una direccion que pueda contener la direccion a una casilla
     * de un arrego (direccionamiento indirecto)
     * @param direccion direccion a revisar
     * @return direccion a utilizar
     */
    private int traduceDirIndirecto(int direccion) {
        if(direccion < 0) {
            direccion = direccion * (-1);
            if(ManejadorMemoria.isGlobal(direccion)) {
                return Integer.parseInt(registroGlobal.getValor(direccion));
            } else {
                return Integer.parseInt(pilaRegistros.peek().getValor(direccion));
            }
        } else {
            return direccion;
        }
    }

    /**
     * Metodo para realizar una operacion aritmetica entre dos operandos
     * @param operando1 direccion del operando 1
     * @param operando2 direccion del operando 2
     * @param valor1 valor del operando 1
     * @param valor2 valor del operando 2
     * @param op codigo del operador aritmetico
     * @return resultado de la operacion aritmetica
     */
    private String auxOperacion(int operando1, int operando2, String valor1, String valor2, int op){
        if (ManejadorMemoria.isInt(operando1)){
            if (ManejadorMemoria.isInt(operando2)){
                switch(op){
                    case Codigos.SUMA:
                        return (Integer.parseInt(valor1) + Integer.parseInt(valor2)) + "";
                    case Codigos.RESTA:
                        return (Integer.parseInt(valor1) - Integer.parseInt(valor2)) + "";
                    case Codigos.MULT:
                        return (Integer.parseInt(valor1) * Integer.parseInt(valor2)) + "";
                    case Codigos.DIV:
                        return (Integer.parseInt(valor1) / Integer.parseInt(valor2)) + "";
                }
            } else if (ManejadorMemoria.isFloat(operando2)){
                switch(op){
                    case Codigos.SUMA:
                        return (Integer.parseInt(valor1) + Double.parseDouble(valor2)) + "";
                    case Codigos.RESTA:
                        return (Integer.parseInt(valor1) - Double.parseDouble(valor2)) + "";
                    case Codigos.MULT:
                        return (Integer.parseInt(valor1) * Double.parseDouble(valor2)) + "";
                    case Codigos.DIV:
                        return (Integer.parseInt(valor1) / Double.parseDouble(valor2)) + "";
                }
            }
        } else if (ManejadorMemoria.isFloat(operando1)){
            if (ManejadorMemoria.isInt(operando2)){
                switch(op){
                    case Codigos.SUMA:
                        return (Double.parseDouble(valor1) + Integer.parseInt(valor2)) + "";
                    case Codigos.RESTA:
                        return (Double.parseDouble(valor1) - Integer.parseInt(valor2)) + "";
                    case Codigos.MULT:
                        return (Double.parseDouble(valor1) * Integer.parseInt(valor2)) + "";
                    case Codigos.DIV:
                        return (Double.parseDouble(valor1) / Integer.parseInt(valor2)) + "";
                }
            } else if (ManejadorMemoria.isFloat(operando2)) {
                switch(op){
                    case Codigos.SUMA:
                        return (Double.parseDouble(valor1) + Double.parseDouble(valor2)) + "";
                    case Codigos.RESTA:
                        return (Double.parseDouble(valor1) - Double.parseDouble(valor2)) + "";
                    case Codigos.MULT:
                        return (Double.parseDouble(valor1) * Double.parseDouble(valor2)) + "";
                    case Codigos.DIV:
                        return (Double.parseDouble(valor1) / Double.parseDouble(valor2)) + "";
                }
            }
        } else if (ManejadorMemoria.isString(operando1)){
            return valor1 + valor2;
        }

        return "";
    }

    public void suma (int dirOp1, int dirOp2, int dirRes) {
        //traduce casos de direccionamiento indirecto
        dirOp1 = traduceDirIndirecto(dirOp1);
        dirOp2 = traduceDirIndirecto(dirOp2);
        if (ManejadorMemoria.isConstante(dirOp1)){ // OPERANDO 1 CTE
            String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 =this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { //RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            }
        } else if (ManejadorMemoria.isGlobal(dirOp1)){ // OPERANDO 1 GLOBAL
            String valor1 = this.registroGlobal.getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            }
        } else { // OPERANDO 1 LOCAL
            String valor1 = this.pilaRegistros.peek().getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { // RES 2 LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else { //OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            }
        }
    }

    public void resta (int dirOp1, int dirOp2, int dirRes) {
        //traduce casos de direccionamiento indirecto
        dirOp1 = traduceDirIndirecto(dirOp1);
        dirOp2 = traduceDirIndirecto(dirOp2);
        if (ManejadorMemoria.isConstante(dirOp1)){ // OPERANDO 1 CTE
            String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 =this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { //RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            }
        } else if (ManejadorMemoria.isGlobal(dirOp1)){ // OPERANDO 1 GLOBAL
            String valor1 = this.registroGlobal.getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            }
        } else { // OPERANDO 1 LOCAL
            String valor1 = this.pilaRegistros.peek().getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { // RES 2 LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            } else { //OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.RESTA));
                }
            }
        }
    }

    public void multiplica (int dirOp1, int dirOp2, int dirRes) {
        //traduce casos de direccionamiento indirecto
        dirOp1 = traduceDirIndirecto(dirOp1);
        dirOp2 = traduceDirIndirecto(dirOp2);
        if (ManejadorMemoria.isConstante(dirOp1)){ // OPERANDO 1 CTE
            String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 =this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { //RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            }
        } else if (ManejadorMemoria.isGlobal(dirOp1)){ // OPERANDO 1 GLOBAL
            String valor1 = this.registroGlobal.getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            }
        } else { // OPERANDO 1 LOCAL
            String valor1 = this.pilaRegistros.peek().getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { // RES 2 LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            } else { //OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.MULT));
                }
            }
        }
    }

    public void divide (int dirOp1, int dirOp2, int dirRes) {
        //traduce casos de direccionamiento indirecto
        dirOp1 = traduceDirIndirecto(dirOp1);
        dirOp2 = traduceDirIndirecto(dirOp2);
        if (ManejadorMemoria.isConstante(dirOp1)){ // OPERANDO 1 CTE
            String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 =this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { //RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            }
        } else if (ManejadorMemoria.isGlobal(dirOp1)){ // OPERANDO 1 GLOBAL
            String valor1 = this.registroGlobal.getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            } else { // OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            }
        } else { // OPERANDO 1 LOCAL
            String valor1 = this.pilaRegistros.peek().getValor(dirOp1);
            if (ManejadorMemoria.isGlobal(dirOp2)){ // OPERANDO 2 GLOBAL
                String valor2 = this.registroGlobal.getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { // RES 2 LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){ // OPERANDO 2 CTE
                String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            } else { //OPERANDO 2 LOCAL
                String valor2 = this.pilaRegistros.peek().getValor(dirOp2);
                if (ManejadorMemoria.isGlobal(dirRes)){ // RES GLOBAL
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                } else { // RES LOCAL
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.DIV));
                }
            }
        }
    }

    /**
     * Metodo para asignar un valor a una direccion
     * @param dirValor direccion del valor a ser asignado
     * @param dirAsigna direccion a la cual se asigna el valor
     */
    public void asigna (int dirValor, int dirAsigna) {
        //traduce casos de direccionamiento indirecto
        dirValor = traduceDirIndirecto(dirValor);
        dirAsigna = traduceDirIndirecto(dirAsigna);
        // Caso en que se asigna a una variable global
        if(ManejadorMemoria.isGlobal(dirAsigna)) {
            // caso en que global = cte
            if (ManejadorMemoria.isConstante(dirValor)){
                String valorCte = directorioProcedimientos.getConstantes()
                        .get(dirValor).getValorConstante();
                this.registroGlobal.guardaValor(dirAsigna, valorCte);
            } else if(ManejadorMemoria.isGlobal(dirValor)){ // caso en que global = global
                String valorCte = this.registroGlobal.getValor(dirValor);
                this.registroGlobal.guardaValor(dirAsigna, valorCte);
            } else { // caso en que global = local
                String valor = this.pilaRegistros.peek().getValor(dirValor);
                this.registroGlobal.guardaValor(dirAsigna,valor);
            }
        } else { // caso en que se asigna a una variable local
            if (ManejadorMemoria.isConstante(dirValor)){ // caso en que local = cte
                String valorCte = directorioProcedimientos.getConstantes()
                        .get(dirValor).getValorConstante();
                this.pilaRegistros.peek().guardaValor(dirAsigna, valorCte);
            } else if (ManejadorMemoria.isGlobal(dirValor)){ // caso en que local = global
                String valor = this.registroGlobal.getValor(dirValor);
                this.pilaRegistros.peek().guardaValor(dirAsigna, valor);
            } else { // caso en que local = local
                String valor = this.pilaRegistros.peek().getValor(dirValor);
                this.pilaRegistros.peek().guardaValor(dirAsigna,valor);
            }
        }
    }

    public void imprime(int direccionValor){
        //traduce casos de direccionamiento indirecto
        direccionValor = traduceDirIndirecto(direccionValor);
        if (ManejadorMemoria.isGlobal(direccionValor)){
            System.out.println(this.registroGlobal.getValor(direccionValor));
        } else {
            System.out.println(this.pilaRegistros.peek().getValor(direccionValor));
        }
    }

    /**
     * Metodo para verificar que una expresion de indexamiento no supere los limites de casillas de un arreglo
     * @param direccionValor es la direccion de memoria donde se encuentra el valor del indexamiento
     * @param limite es la cantidad de casillas del arreglo
     */
    public void verifica(int direccionValor, int limite){
        String valorIndex;
        // obtener el valor dentro de la direccion recibida
        if (ManejadorMemoria.isGlobal(direccionValor)){
            valorIndex = this.registroGlobal.getValor(direccionValor);
        } else if (ManejadorMemoria.isConstante(direccionValor)){
            valorIndex = this.directorioProcedimientos.getConstantes().get(direccionValor).getValorConstante();
        } else {
           valorIndex =  this.pilaRegistros.peek().getValor(direccionValor);
        }
        // revisar que el valor no sobrepase el limite del arreglo
        if (Integer.parseInt(valorIndex) >= limite) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Metodo para realizar la suma de la direccion base del arreglo con el resultado de la expresion de indexamiento.
     * El resultado se guarda en un valor temporal
     * @param direccionValorIndex es la direccion local o global donde esta el resultado de la expresion de indexamiento
     * @param direccionBase es la direccion base del arreglo
     * @param direccionResultadoOffset es la direccion del temporal donde se guardara la direccion de la casilla accesada del arreglo
     */
    public void sumaOffset(int direccionValorIndex, int direccionBase, int direccionResultadoOffset){
        String valorIndex;
        // obtener el valor dentro de la direccion recibida
        if (ManejadorMemoria.isGlobal(direccionValorIndex)){
            valorIndex = this.registroGlobal.getValor(direccionValorIndex);
        }else if (ManejadorMemoria.isConstante(direccionValorIndex)){
            valorIndex = this.directorioProcedimientos.getConstantes().get(direccionValorIndex).getValorConstante();
        } else {
            valorIndex =  this.pilaRegistros.peek().getValor(direccionValorIndex);
        }
        // sumar la expresion de indexamiento con la direccion base y guardarlo en el mapa de memoria local
        String direccionMemoriaCasilla = (direccionBase + Integer.parseInt(valorIndex)) + "";
        this.pilaRegistros.peek().guardaValor(direccionResultadoOffset,direccionMemoriaCasilla);
    }

}
