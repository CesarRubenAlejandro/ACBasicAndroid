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

    public void suma (int dirOp1, int dirOp2, int dirRes) {

    }

    public void resta (int dirOp1, int dirOp2, int dirRes) {

    }

    public void multiplica (int dirOp1, int dirOp2, int dirRes) {

    }

    public void divide (int dirOp1, int dirOp2, int dirRes) {

    }

    public void asigna (int dirValor, int dirAsigna) {
        if(ManejadorMemoria.isGlobal(dirAsigna)) {
            if (ManejadorMemoria.isConstante(dirValor)){
                String valorCte = directorioProcedimientos.getConstantes()
                        .get(dirValor).getValorConstante();
                this.registroGlobal.guardaValor(dirAsigna, valorCte);
            } else {

            }
        } else {

        }
    }

}
