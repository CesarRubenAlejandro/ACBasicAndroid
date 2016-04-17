package itesm.mx.acbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Stack;

import itesm.mx.acbasic.Data.Codigos;
import itesm.mx.acbasic.Data.DirectorioProcedimientos;
import itesm.mx.acbasic.Data.MaquinaVirtual;


public class MainActivity extends AppCompatActivity {
    private Button bttnCompilar;
    private EditText codigoET;
    private TextView outputTV;
    private EditText inputBoxET;
    private ACBasic parser;

    private int[][] matrizCuadruplos;
    private int instructionPointer;
    private DirectorioProcedimientos dirProcedimientos;
    private Stack<Integer> pilaInstrucciones;
    private MaquinaVirtual maquinaVirtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttnCompilar = (Button) findViewById(R.id.button);
        codigoET = (EditText) findViewById(R.id.codigoEntrada);
        outputTV = (TextView) findViewById(R.id.output);
        inputBoxET = (EditText) findViewById(R.id.inputBox);

        bttnCompilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entrada = codigoET.getText().toString();
                InputStream is = new ByteArrayInputStream(entrada.getBytes());
                if (parser == null){
                    parser = new ACBasic(is);
                } else {
                    parser.ReInit(is);
                }
                try {
                    parser.init();
                    parser.prog();
                    System.out.println("Parser OK");
                    dirProcedimientos = parser.getDirProcedimientos();
                    matrizCuadruplos = parser.getMatrizCuadruplos();
                    instructionPointer = 0;
                    pilaInstrucciones = new Stack<Integer>();
                    maquinaVirtual = new MaquinaVirtual(dirProcedimientos);
                    ejecutar();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ejecutar () {
        while (instructionPointer < matrizCuadruplos.length) {
            System.out.println(instructionPointer + ":" + matrizCuadruplos[instructionPointer][0] + " "
                    + matrizCuadruplos[instructionPointer][1] + " " + matrizCuadruplos[instructionPointer][2] + " "
                    + matrizCuadruplos[instructionPointer][3] + " ");

            switch (matrizCuadruplos[instructionPointer][0]) {
                case Codigos.SUMA:
                    maquinaVirtual.suma(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    break;
                case Codigos.RESTA:
                    maquinaVirtual.resta(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    break;
                case Codigos.MULT:
                    maquinaVirtual.multiplica(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    break;
                case Codigos.DIV:
                    maquinaVirtual.divide(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    break;
                case Codigos.ASSIGN:
                    maquinaVirtual.asigna(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][3]);
                    break;

                case Codigos.PRINT:
                    maquinaVirtual.imprime(matrizCuadruplos[instructionPointer][3]);

            }
            instructionPointer++;
        }
    }
}
