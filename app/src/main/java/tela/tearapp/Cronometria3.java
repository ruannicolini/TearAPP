package tela.tearapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import domain.Cronometragem;

public class Cronometria3 extends Activity {
    Date dataEscolhida;
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

        //Data não pode ser alterada, sempre a do dia.
        dataEscolhida = new Date();
        TextView editData = (TextView) findViewById(R.id.EditData);
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        editData.setText(currentDateTimeString);
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
        TextView editRitmo = (TextView) findViewById(R.id.EditRitmo);
        TextView editNumPecas = (TextView) findViewById(R.id.EditNumPecas);
        TextView editComprimento = (TextView) findViewById(R.id.EditComprimento);
        TextView editTolerancia = (TextView) findViewById(R.id.EditTolerancia);
        TextView editReferencia = (TextView) findViewById(R.id.EditReferencia);
        TextView editNumOcorrencia = (TextView) findViewById(R.id.EditNumOcorrencia);
        TextView editData = (TextView) findViewById(R.id.EditData);

        //Todos os campos devem estar preenchidos!
        if((!editRitmo.getText().toString().equals(""))
                ||(!editNumPecas.getText().toString().equals(""))
                ||(!editComprimento.getText().toString().equals(""))
                ||(!editTolerancia.getText().toString().equals(""))
                ||(!editNumOcorrencia.getText().toString().equals(""))){

            cronometragem.setRitmo(Integer.parseInt(editRitmo.getText().toString()));
            cronometragem.setNumPecas(Integer.parseInt(editNumPecas.getText().toString()));
            cronometragem.setComprimentoProduto(Float.parseFloat(editComprimento.getText().toString()));
            cronometragem.setTolerancia(Integer.parseInt(editTolerancia.getText().toString()));
            cronometragem.setReferencia(editReferencia.getText().toString());
            cronometragem.setNumOcorrencia(Integer.parseInt(editNumOcorrencia.getText().toString()));
            cronometragem.setDataCronometragem(dataEscolhida);

            //Passa Os dados obtidos pra Activity/tela Cronometria4
            Intent intent = new Intent(getApplicationContext(), Cronometria4.class);
            Bundle args = new Bundle();
            args.putSerializable("cronometragem", cronometragem);
            intent.putExtras(args);
            startActivity(intent);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Todos os campos devem estar  preenchidos!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
