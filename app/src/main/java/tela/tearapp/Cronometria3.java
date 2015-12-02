package tela.tearapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import domain.Cronometragem;

public class Cronometria3 extends Activity {
    int ano, mes, dia;
    Calendar calendario;
    Cronometragem cronometragem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria3);
        cronometragem = new Cronometragem();

        // Recebe Parametros da Activity Cronometria
        Bundle params = getIntent().getExtras();
        if(params != null){
            cronometragem = (Cronometragem) params.getSerializable("cronometragem");
            System.out.println("Chegou na Cronometria3");
        }
        calendario = Calendar.getInstance();
        //ano = calendario.get(Calendar.DAY_OF_MONTH);
        //mes = calendario.get(Calendar.MONTH);
        //ano = calendario.get(Calendar.YEAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cronometria3, menu);
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

    public void chamaCronometria4(View view){


        //Passa Os dados obtidos pra Activity/tela Cronometria4
        Intent intent = new Intent(getApplicationContext(), Cronometria4.class);
        Bundle args = new Bundle();
        args.putSerializable("cronometragem", cronometragem);
        intent.putExtras(args);
        startActivity(intent);
        startActivity(intent);
    }

    //DATA
    public void selecionarData(View v){
        showDialog(v.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(R.id.btnData == id){
            return new DatePickerDialog(this, listener, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            TextView textViewData = (TextView) findViewById(R.id.EditData);
            textViewData.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        }
    };
}
