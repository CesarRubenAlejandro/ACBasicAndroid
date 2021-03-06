package itesm.mx.acbasic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Stack;

import itesm.mx.acbasic.Data.Codigos;
import itesm.mx.acbasic.Data.DirectorioProcedimientos;
import itesm.mx.acbasic.Data.MaquinaVirtual;
import itesm.mx.acbasic.Data.Procedimiento;

/**
 * Actividad principal de la app
 */
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    // guarda el valor del IP antes de realizar la operacion de read oara despues reanudar la ejecucion de cuadruplos
    private int auxiliarInstructionPointer;

    private ImageButton bttnCompilar;
    private ImageButton bttnClean;
    private ImageButton bttnTrash;
    private ImageButton bttnHelp;
    private ImageButton bttnTemplate;
    private EditText codigoET;
    private TextView outputTV;
    private ACBasic parser;

    // codigo intermedio creado por el analizador
    private int[][] matrizCuadruplos;
    // apuntador a la instruccion a ejecutar
    private int instructionPointer;
    private DirectorioProcedimientos dirProcedimientos;
    // objeto maquina virtual
    private MaquinaVirtual maquinaVirtual;
    // cantidad de cuadruplos generados por el analizador
    private int contadorCuadruplos;

    @Override
    /**
     * Metodo ejecutado al iniciar la aplicacion
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttnCompilar = (ImageButton) findViewById(R.id.ejecBtn);
        bttnClean = (ImageButton)  findViewById(R.id.cleanBtn);
        bttnTrash = (ImageButton) findViewById(R.id.trashBtn);
        bttnHelp = (ImageButton) findViewById(R.id.helpBtn);
        bttnTemplate = (ImageButton) findViewById(R.id.templateBtn);
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
                    maquinaVirtual = new MaquinaVirtual(dirProcedimientos);
                    outputTV.setText("");
                    ejecutar();
                } catch (ParseException e) {
                    e.printStackTrace();
                    outputTV.setText(Html.fromHtml("<font color=#cc0029>" + e.getMessage() + "</font>"));
                }

            }
        });

        bttnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputTV.setText("");
            }
        });

        bttnTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigoET.setText("");
                outputTV.setText("");
            }
        });

        bttnTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigoET.setText("program ejemplo;\n" +
                        "\n" +
                        "func void funcion1() {\n" +
                        "\n" +
                        "}\n" +
                        "\n" +
                        "main() {\n" +
                        "\n" +
                        "}");
                outputTV.setText("");
            }
        });


        bttnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHelp = new Intent (MainActivity.this, HelpActivity.class);
                startActivity(intentHelp);
            }
        });

        if (savedInstanceState != null) {
            codigoET.setText(savedInstanceState.getString("codigo"));
            outputTV.setText(savedInstanceState.getString("output"));
        }
    }

    @Override
    /**
     * Metodo ejecutado cuando se cierra la actividad
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("codigo", codigoET.getText().toString());
        savedInstanceState.putString("output", outputTV.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
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

    /**
     * Metodo que lee todos los cuadruplos de compilacion y procesa las acciones a la maquina virtual
     */
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
                    instructionPointer++;
                    break;
                case Codigos.RESTA:
                    maquinaVirtual.resta(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.MULT:
                    maquinaVirtual.multiplica(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.DIV:
                    maquinaVirtual.divide(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;

                case Codigos.ASSIGN:
                    maquinaVirtual.asigna(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;

                case Codigos.PRINT:
                    String auxImprime = maquinaVirtual.imprime(matrizCuadruplos[instructionPointer][3]);
                    outputTV.append(Html.fromHtml( "<br/>" +  "<font color=#000000>"  + auxImprime +  "</font>"));
                    instructionPointer++;
                    break;

                case Codigos.VERIFICAR:
                    if (!maquinaVirtual.verifica(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][3])){
                        outputTV.append( Html.fromHtml("<font color=#cc0029> Indice de arreglo fuera de limite </font>"));
                        instructionPointer = contadorCuadruplos;
                    } else {
                        instructionPointer++;
                    }
                    break;

                case Codigos.SUMAOFFSET:
                    maquinaVirtual.sumaOffset(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][2], matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;

                case Codigos.MAYOR:
                    maquinaVirtual.mayor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.MENOR:
                    maquinaVirtual.menor(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.MAYORIG:
                    maquinaVirtual.mayoroigual(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.MENORIG:
                    maquinaVirtual.menoroigual(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.IGUAL:
                    maquinaVirtual.igualigual(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.DIFERENTE:
                    maquinaVirtual.diferente(matrizCuadruplos[instructionPointer][1],
                            matrizCuadruplos[instructionPointer][2],
                            matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.GOTO:
                    instructionPointer = matrizCuadruplos[instructionPointer][3];
                    break;
                case Codigos.GOTOF:
                    if (!maquinaVirtual.gotoFalso(matrizCuadruplos[instructionPointer][1])) {
                        instructionPointer = matrizCuadruplos[instructionPointer][3];
                    } else {
                        instructionPointer++;
                    }
                    break;
                case Codigos.READ:
                    // terminar la ejecucion momentaneamente mientras se lee el valor
                    auxiliarInstructionPointer = instructionPointer;
                    instructionPointer = contadorCuadruplos;
                    Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
                case Codigos.AND:
                    maquinaVirtual.and(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][2]
                            , matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.OR:
                    maquinaVirtual.or(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][2]
                            , matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.NOT:
                    maquinaVirtual.not(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.ERA:
                    maquinaVirtual.era(matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.PARAM:
                    maquinaVirtual.param(matrizCuadruplos[instructionPointer][1], matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.GOSUB:
                    instructionPointer = maquinaVirtual.gosub(matrizCuadruplos[instructionPointer][3], instructionPointer);
                    break;
                case Codigos.ENDPROC:
                    instructionPointer = maquinaVirtual.endproc();
                    break;
                case Codigos.RETURN:
                    maquinaVirtual.retorno(matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
                case Codigos.ASSIGNRET:
                    maquinaVirtual.assignret(matrizCuadruplos[instructionPointer][1],matrizCuadruplos[instructionPointer][3]);
                    instructionPointer++;
                    break;
            }

        }
    }

    /**
     * Metodo callback llamado cuando se regresa de la actividad de input
     * @param requestCode es el codigo original de llamada
     * @param resultCode es el codigo resultado
     * @param data es la coleccion de datos enviada por la actividad de input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode== REQUEST_CODE) && (resultCode==RESULT_OK)){
            Bundle datos = data.getExtras();
            outputTV.append(Html.fromHtml("<br/> <font color=#008080>" + datos.getString("valor") + "</font>"));
            try {
                maquinaVirtual.read(matrizCuadruplos[auxiliarInstructionPointer][3], datos.getString("valor"));
                instructionPointer = auxiliarInstructionPointer + 1;
                ejecutar();
            } catch (Exception e) {
                outputTV.append( Html.fromHtml("<font color=#cc0029> Tipo de dato incorrecto </font>"));
                instructionPointer = contadorCuadruplos;
            }
        }
    }
}
