package tela.tearapp;

import android.app.Activity;
import android.content.Context;
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
import java.util.List;
import java.util.Vector;

import dao.TipoRecursoDao;
import dao.TipoRecursoJDBCDao;
import domain.Batida;
import domain.Cronometragem;
import domain.Grupo;
import domain.TipoRecurso;

public class Cronometria4 extends Activity {

    final Cronometragem cronometragem = new Cronometragem();
    final TipoRecursoJDBCDao tipoRecursoDao = new TipoRecursoJDBCDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria4);
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
        recursos = tipoRecursoDao.obterTiposRecurso();

        if(recursos != null){
            //Adapter
            final ArrayAdapter<TipoRecurso> adapter = new ArrayAdapter<TipoRecurso>(this,R.layout.item_consulta,recursos);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    TipoRecurso o = (TipoRecurso)lv.getItemAtPosition(position);

                    //FAZER AQUI A PARTE DE MOSTRAR O RECURSO ESCOLHIDO NO LISTVIEW

                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);

                    //Add o Tipo de Recurso no arrayList do Objeto Cronometragem
                    cronometragem.getRecursos().add(o);
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
