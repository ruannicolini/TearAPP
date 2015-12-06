package tela.tearapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import baseAdapter.TipoRecursoAdapter;
import dao.CronometragemJDBCDao;
import dao.CronometragemSQLite;
import dao.TipoRecursoDao;
import dao.TipoRecursoJDBCDao;
import dao.TipoRecursoSQLite;
import domain.Batida;
import domain.Cronometragem;
import domain.Grupo;
import domain.TipoRecurso;
import util.MyDialogFragment;
import util.NullConnectionException;

public class Cronometria4 extends Activity implements MyDialogFragment.MyDialogFragmentListener{
    TipoRecursoJDBCDao tipoRecursoJDBCDao;
    TipoRecursoSQLite tipoRecursoSQLite;
    CronometragemJDBCDao cronometragemJDBCDao;
    CronometragemSQLite cronometragemSQLite;
    Cronometragem cronometragem;

    //VAR realacionadas a Lista de Recursos usados na cronometragem
    ArrayAdapter<TipoRecurso> adapterRecurso;
    ListView lvRecurso;
    //=======================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria4);

        //Principal.onOff == true
            tipoRecursoJDBCDao = new TipoRecursoJDBCDao();
            cronometragemJDBCDao = new CronometragemJDBCDao();
        //Principal.onOff == false
            tipoRecursoSQLite = new TipoRecursoSQLite(Principal.database);
            cronometragemSQLite = new CronometragemSQLite(Principal.database);

        cronometragem = new Cronometragem();

        // Recebe Parametros da Activity Cronometria
        Bundle params = getIntent().getExtras();
        if(params != null){
            cronometragem = (Cronometragem) params.getSerializable("cronometragem");
            System.out.println("Chegou na Cronometria4");
        }

        cronometragem.setRecursos(new ArrayList<TipoRecurso>());
        //Seta Adapter LVRECURSO
        adapterRecurso = new ArrayAdapter<TipoRecurso>(this,R.layout.item_consulta, cronometragem.getRecursos());
        lvRecurso = (ListView) findViewById(R.id.listViewRecurso);
        lvRecurso.setAdapter(adapterRecurso);

        //Comportamento do Click em um item da Lista
        lvRecurso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                final TipoRecurso recurso = (TipoRecurso) lv.getItemAtPosition(position);
                //Remove Recurso
                cronometragem.getRecursos().remove(recurso);
                adapterRecurso.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cronometria4, menu);
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

    public void buscaRecurso(View view) throws SQLException {

        Vector<TipoRecurso> recursos = new Vector();
        Boolean status = false;
        //Modo de operação do APP
        while(status == false) {
            if (Principal.onOff == true) {
                try {
                    recursos = tipoRecursoJDBCDao.obterTiposRecurso();
                    status = true;
                }catch (NullConnectionException ne){
                    OpenDialog("\"Sem Conexão! APP operando em modo OFF.\"");
                    Principal.onOff = false;
                }
            } else {
                recursos = tipoRecursoSQLite.obterTiposRecurso();
                status = true;
            }
        }

        if(recursos != null){
            //Adapter
            final ArrayAdapter<TipoRecurso> adapter = new ArrayAdapter<TipoRecurso>(this,R.layout.item_consulta,recursos);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    TipoRecurso o = (TipoRecurso) lv.getItemAtPosition(position);

                    //Esconde o FrameLayoutCronometria
                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);

                    if(cronometragem.getRecursos().contains(o) == false){
                        //Add o Tipo de Recurso no arrayList do Objeto Cronometragem e na ListView
                        cronometragem.getRecursos().add(o);
                        adapterRecurso.notifyDataSetChanged();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Recurso já incluso.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
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
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.ConsultaNull), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void salvarCronometragem(View view){
        try {
            if(Principal.onOff == true){
                cronometragemJDBCDao.inserirCronometragem(cronometragem);
            }else{
                cronometragemSQLite.inserirCronometragem(cronometragem);
            }

            Toast toast = Toast.makeText(getApplicationContext(), "Cronometria Enviada!", Toast.LENGTH_SHORT);
            toast.show();
            Intent returnBtn = new Intent(this, Principal.class);
            startActivity(returnBtn);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void cancelarCronometragem(View view){
        Intent returnBtn = new Intent("android.intent.action.MAIN");
        startActivity(returnBtn);
    }

    public void OpenDialog(String msg) {
        MyDialogFragment myDialogFragment = MyDialogFragment.newInstance(msg);
        myDialogFragment.show(getFragmentManager(), "myDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) { }
}
