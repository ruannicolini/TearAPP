package tela.tearapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import dao.CronometristaJDBCDao;
import domain.Cronometrista;

public class Cronometria extends FragmentActivity {

    CronometristaJDBCDao cronometristaDao = new CronometristaJDBCDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cronometria1, menu);
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

    public void buscaCronometrista(View view){
        final EditText editIdCronometrista = (EditText)findViewById(R.id.EditIdCronometria), editCronometrista = (EditText)findViewById(R.id.EditCronometria);
        Vector<Cronometrista> cronometristas = new Vector();
        cronometristas = cronometristaDao.obterCronometristas();

        if(cronometristaDao != null){
            //Adapter
            final ArrayAdapter<Cronometrista> adapter = new ArrayAdapter<Cronometrista>(this,android.R.layout.simple_list_item_1,cronometristas);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    Cronometrista o = (Cronometrista)lv.getItemAtPosition(position);
                    String idC = String.valueOf(o.getIdCronometrista());
                    String nomeC = o.getNome();
                    editIdCronometrista.setText (idC);
                    editCronometrista.setText(nomeC);

                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);
                }
            });

            //Deixa o FrameLayout Visivel
            FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
            fl.setVisibility(View.VISIBLE);

            // Adiciona um TextWatcher
            EditText filter = (EditText) findViewById(R.id.editPesq);
            filter.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) { }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Aplica o filtro no Adapter
                    adapter.getFilter().filter(s.toString());
                }
            });
        }else {
            Context contexto = getApplicationContext();
            String texto = getString(R.string.ConsultaNull);
            int duracao = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(contexto, texto, duracao);
            toast.show();
        }

    }
}
