package itesm.mx.acbasic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    private static final int REQUEST_CODE = 1;
    private int auxiliarInstructionPointer;

    private Button bttnCompilar;
    private EditText codigoET;
    private TextView outputTV;
    private ACBasic parser;

    private int[][] matrizCuadruplos;
    private int instructionPointer;
    private DirectorioProcedimientos dirProcedimientos;
    private Stack<Integer> pilaInstrucciones;
    private MaquinaVirtual maquinaVirtual;
    private int contadorCuadruplos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttnCompilar = (Button) findViewById(R.id.button);
        codigoET = (EditText) findViewById(R.id.codigoEntrada);
        outputTV = (TextView) findViewById(R.id.output);

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
                    contadorCuadruplos = parser.getContadorCuadruplo();
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
        while (instructionPointer < contadorCuadruplos) {
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
                    String auxImprime = maquinaVirtual.imprime(matrizCuadruplos[instructionPointer][3]);
                    outputTV.setText(outputTV.getText().toString() + '\n' + auxImprime);
                    break;

                case Codigos.VERIFICAR:
                    maquinaVirtual.verifica(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][3]);
                    break;

                case Codigos.SUMAOFFSET:
                    maquinaVirtual.sumaOffset(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][2], matrizCuadruplos[instructionPointer][3]);
                    break;

                case Codigos.MAYOR:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);

                case Codigos.MENOR:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);

                case Codigos.MAYORIG:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);

                case Codigos.MENORIG:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);

                case Codigos.IGUAL:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);

                case Codigos.DIFERENTE:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                case Codigos.GOTO:
                    instructionPointer = matrizCuadruplos[instructionPointer][3];
                    break;
                case Codigos.GOTOF:
                    if (!maquinaVirtual.gotoFalso(matrizCuadruplos[instructionPointer][1])) {
                        instructionPointer = matrizCuadruplos[instructionPointer][3];
                    }
                    break;
                case Codigos.READ:
                    // terminar la ejecucion momentaneamente mientras se lee el valor
                    auxiliarInstructionPointer = instructionPointer;
                    instructionPointer = contadorCuadruplos;
                    Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
            }
            instructionPointer++;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode== REQUEST_CODE) && (resultCode==RESULT_OK)){
            System.out.println("REGRESEE");
            Bundle datos = data.getExtras();
            maquinaVirtual.read(matrizCuadruplos[auxiliarInstructionPointer][3], datos.getString("valor"));
            instructionPointer = auxiliarInstructionPointer + 1;
            ejecutar();
        }
    }
}
