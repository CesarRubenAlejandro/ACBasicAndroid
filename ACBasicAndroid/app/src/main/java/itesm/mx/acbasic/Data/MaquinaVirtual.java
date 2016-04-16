package itesm.mx.acbasic.Data;

import java.util.Stack;

/**
 * Created by Cesar on 4/16/2016.
 */
public class MaquinaVirtual {
    private DirectorioProcedimientos directorioProcedimientos;
    private Registro registroGlobal;
    private Stack<Registro> pilaRegistros;
    private Stack<Integer> pilaInstrucciones;
    private String valorRetorno;

    public MaquinaVirtual(DirectorioProcedimientos directorioProcedimientos) {
        this.directorioProcedimientos = directorioProcedimientos;
        registroGlobal = new Registro();
        pilaRegistros = new Stack<Registro>();
        pilaInstrucciones = new Stack<Integer>();
        valorRetorno = "";
    }


}
