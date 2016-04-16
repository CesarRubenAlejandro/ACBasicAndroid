package itesm.mx.acbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import itesm.mx.acbasic.Data.DirectorioProcedimientos;


public class MainActivity extends AppCompatActivity {
    private Button bttnCompilar;
    private EditText codigoET;
    private ACBasic parser;
    private int[][] matrizCuadruplos;
    private int contadorCuadruplo;
    private DirectorioProcedimientos dirProcedimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bttnCompilar = (Button) findViewById(R.id.button);
        codigoET = (EditText) findViewById(R.id.editText);

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
                    contadorCuadruplo = parser.getContadorCuadruplo();
                    for (int i=0; i<contadorCuadruplo; i++) {
                        System.out.println(i + ": " + matrizCuadruplos[i][0] + " " + matrizCuadruplos[i][1] + " "
                                + matrizCuadruplos[i][2] + " " + matrizCuadruplos[i][3]);
                    }
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
}
