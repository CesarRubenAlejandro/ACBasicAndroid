package itesm.mx.acbasic.Data;

import java.util.Stack;

/**
 * Created by Cesar on 4/16/2016.
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
    }

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
        if (ManejadorMemoria.isConstante(dirOp1)){

            if (ManejadorMemoria.isGlobal(dirOp2)){
                if (ManejadorMemoria.isGlobal(dirRes)){
                    String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
                    String valor2 = this.registroGlobal.getValor(dirOp2);
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.SUMA));
                } else {
                    String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
                    String valor2 = this.registroGlobal.getValor(dirOp2);
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){
                if (ManejadorMemoria.isGlobal(dirRes)){
                    String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
                    String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                    this.registroGlobal.guardaValor(dirRes,auxOperacion(dirOp1,dirOp2,valor1,valor2,Codigos.SUMA));
                } else {
                    String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
                    String valor2 = this.directorioProcedimientos.getConstantes().get(dirOp2).getValorConstante();
                    this.pilaRegistros.peek().guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                }
            } else {
                if (ManejadorMemoria.isGlobal(dirRes)){
                    String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
                    String valor2 =this.pilaRegistros.peek().getValor(dirOp2);
                    this.registroGlobal.guardaValor(dirRes, auxOperacion(dirOp1, dirOp2, valor1, valor2, Codigos.SUMA));
                } else {
                    String valor1 = this.directorioProcedimientos.getConstantes().get(dirOp1).getValorConstante();
                    String valor2 =this.pilaRegistros.peek().getValor(dirOp2);
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

            if (ManejadorMemoria.isGlobal(dirOp2)){
                if (ManejadorMemoria.isGlobal(dirRes)){

                } else {

                }
            } else if (ManejadorMemoria.isConstante(dirOp2)){
                if (ManejadorMemoria.isGlobal(dirRes)){

                } else {

                }
            } else {
                if (ManejadorMemoria.isGlobal(dirRes)){

                } else {

                }
            }
        }
    }

    public void resta (int dirOp1, int dirOp2, int dirRes) {

    }

    public void multiplica (int dirOp1, int dirOp2, int dirRes) {

    }

    public void divide (int dirOp1, int dirOp2, int dirRes) {

    }

    public void asigna (int dirValor, int dirAsigna) {
        // Caso en que se asigna a una variable global
        if(ManejadorMemoria.isGlobal(dirAsigna)) {
            // caso en que se global = cte
            if (ManejadorMemoria.isConstante(dirValor)){
                String valorCte = directorioProcedimientos.getConstantes()
                        .get(dirValor).getValorConstante();
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

}
