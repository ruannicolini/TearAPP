package tela.tearapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import dao.CronometragemJDBCDao;
import dao.CronometristaJDBCDao;
import dao.CronometristaSQLite;
import dao.GrupoJDBCDao;
import dao.GrupoSQLite;
import dao.OperacaoJDBCDao;
import dao.OperacaoSQLite;
import dao.OperadorJDBCDao;
import dao.OperadorSQLite;
import dao.ProdutoJDBCDao;
import dao.ProdutoSQLite;
import dao.TecidoJDBCDao;
import dao.TecidoSQLite;
import domain.Cronometragem;
import domain.Cronometrista;
import domain.Grupo;
import domain.Operacao;
import domain.Operador;
import domain.Produto;
import domain.Tecido;
import util.MyDialogFragment;

public class Cronometria extends FragmentActivity implements MyDialogFragment.MyDialogFragmentListener {

    CronometristaJDBCDao cronometristaJDBCDao;
    CronometristaSQLite cronometristaSQLite;
    GrupoJDBCDao grupoJDBCDao;
    GrupoSQLite grupoSQLite;
    TecidoJDBCDao tecidoJDBCDao;
    TecidoSQLite tecidoSQLite;
    OperadorJDBCDao operadorJDBCDao;
    OperadorSQLite operadorSQLite;
    ProdutoJDBCDao produtoJDBCDao;
    ProdutoSQLite produtoSQLite;
    OperacaoJDBCDao operacaoJDBCDao;
    OperacaoSQLite operacaoSQLite;
    Cronometragem cronometragem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria);

        if(Principal.onOff == true){
            cronometristaJDBCDao = new CronometristaJDBCDao();
            grupoJDBCDao = new GrupoJDBCDao();
            tecidoJDBCDao = new TecidoJDBCDao();
            operadorJDBCDao = new OperadorJDBCDao();
            produtoJDBCDao = new ProdutoJDBCDao();
            operacaoJDBCDao = new OperacaoJDBCDao();
        }else {
            cronometristaSQLite = new CronometristaSQLite(Principal.database);
            grupoSQLite = new GrupoSQLite(Principal.database);
            tecidoSQLite = new TecidoSQLite(Principal.database);
            operadorSQLite = new OperadorSQLite(Principal.database);
            produtoSQLite = new ProdutoSQLite(Principal.database);
            operacaoSQLite = new OperacaoSQLite(Principal.database);
        }
        //Cronometragem
        cronometragem = new Cronometragem();
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

    public void buscaOperacao(View view) throws SQLException {
        final EditText editIdOperacao = (EditText)findViewById(R.id.EditIdOperacao), editOperacao = (EditText)findViewById(R.id.EditOperacao);
        Vector<Operacao> operacoes = new Vector();

        if(Principal.onOff == true) {
            operacoes = operacaoJDBCDao.obterOperacoes();
        }else {
            operacoes = operacaoSQLite.obterOperacoes();
        }

        if(operacoes != null){
            //Adapter
            final ArrayAdapter<Operacao> adapter = new ArrayAdapter<Operacao>(this,R.layout.item_consulta,operacoes);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    Operacao o = (Operacao)lv.getItemAtPosition(position);
                    editOperacao.setText(o.getAcao() + " " + o.getParte() + " " + o.getFase());
                    editIdOperacao.setText(String.valueOf(o.getIdOperacao()));
                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);

                    //Set Operação
                    cronometragem.setOperacao(o);
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

    public void buscaTecido(View view) throws SQLException {
        final EditText editIdTecido = (EditText)findViewById(R.id.EditIdTecido), editTecido = (EditText)findViewById(R.id.EditTecido);
        Vector<Tecido> tecidos = new Vector();

        if(Principal.onOff == true) {
            tecidos = tecidoJDBCDao.obterTecidos();
        }else {
            tecidos = tecidoSQLite.obterTecidos();
        }

        if(tecidos != null){
            //Adapter
            final ArrayAdapter<Tecido> adapter = new ArrayAdapter<Tecido>(this,R.layout.item_consulta,tecidos);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    Tecido o = (Tecido)lv.getItemAtPosition(position);
                    editIdTecido.setText (String.valueOf(o.getIdTecido()));
                    editTecido.setText(o.getDescricao());
                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);

                    //Set Tecido
                    cronometragem.setTecido(o);
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

    public void buscaProduto(View view) throws SQLException {
        final EditText editIdGrupo = (EditText)findViewById(R.id.EditIdProduto), editGrupo = (EditText)findViewById(R.id.EditProduto);
        Vector<Produto> produtos = new Vector();

        if(Principal.onOff == true) {
            produtos = produtoJDBCDao.obterProdutos();
        }else {
            produtos = produtoSQLite.obterProdutos();
        }

        if(produtos != null){
            //Adapter
            final ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this,R.layout.item_consulta,produtos);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    Produto p = (Produto)lv.getItemAtPosition(position);
                    editIdGrupo.setText (String.valueOf(p.getIdProduto()));
                    editGrupo.setText(p.getDescricao());
                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);

                    //Set cronometragem
                    cronometragem.setProduto(p);
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

    public void buscaOperador(View view) throws SQLException {
        final EditText editIdOperador = (EditText)findViewById(R.id.EditIdOperador), editOperador = (EditText)findViewById(R.id.EditOperador);
        Vector<Operador> operadores = new Vector();
        if(cronometragem.getGrupo() != null){

            if(Principal.onOff == true) {
                operadores = operadorJDBCDao.obterOperadoresGrupo(cronometragem.getGrupo());
            }else {
                operadores = operadorSQLite.obterOperadoresGrupo(cronometragem.getGrupo());
            }

            if(operadores != null){
                //Adapter
                final ArrayAdapter<Operador> adapter = new ArrayAdapter<Operador>(this,R.layout.item_consulta,operadores);
                ListView lv = (ListView) findViewById(R.id.listViewCronometria);
                lv.setAdapter(adapter);

                //Comportamento do Click em um item da Lista
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                        Operador o = (Operador)lv.getItemAtPosition(position);
                        String idO = String.valueOf(o.getIdOperador());
                        String nomeO = o.getNome();
                        editIdOperador.setText (idO);
                        editOperador.setText(nomeO);
                        FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                        fl.setVisibility(View.GONE);
                        //Set Operador
                        cronometragem.setOperador(o);
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

        }else{
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.GrupoNull), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void buscaGrupo(View view) throws SQLException {
        final EditText editIdGrupo = (EditText)findViewById(R.id.EditIdGrupo), editGrupo = (EditText)findViewById(R.id.EditGrupo);
        Vector<Grupo> grupos = new Vector();

        if(Principal.onOff == true) {
            grupos = grupoJDBCDao.obterGrupos();
        }else {
            grupos = grupoSQLite.obterGrupos();
        }

        if(grupos != null){
            //Adapter
            final ArrayAdapter<Grupo> adapter = new ArrayAdapter<Grupo>(this,R.layout.item_consulta,grupos);
            ListView lv = (ListView) findViewById(R.id.listViewCronometria);
            lv.setAdapter(adapter);

            //Comportamento do Click em um item da Lista
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                    Grupo o = (Grupo)lv.getItemAtPosition(position);
                    String idG = String.valueOf(o.getIdGrupo());
                    String nomeG = o.getDescricao();
                    editIdGrupo.setText (idG);
                    editGrupo.setText(nomeG);
                    FrameLayout fl = (FrameLayout) findViewById(R.id.frameLayoutCronometria);
                    fl.setVisibility(View.GONE);

                    //Set cronometragem
                    cronometragem.setGrupo(o);
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
            Toast toast = Toast.makeText( getApplicationContext(),getString(R.string.ConsultaNull), Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void buscaCronometrista(View view) throws SQLException {
        OpenDialog();
        final EditText editIdCronometrista = (EditText)findViewById(R.id.EditIdCronometria), editCronometrista = (EditText)findViewById(R.id.EditCronometria);
        Vector<Cronometrista> cronometristas = new Vector();

        if(Principal.onOff == true) {
            cronometristas = cronometristaJDBCDao.obterCronometristas();
        }else {
            cronometristas = cronometristaSQLite.obterCronometristas();
        }

        if(cronometristas != null){
            //Adapter
            final ArrayAdapter<Cronometrista> adapter = new ArrayAdapter<Cronometrista>(this,R.layout.item_consulta,cronometristas);
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

                    //Set Cronometrista
                    cronometragem.setCronometrista(o);
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
            Toast toast = Toast.makeText( getApplicationContext(),getString(R.string.ConsultaNull), Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void chamaCronometria2(View view) throws SQLException {
        CronometragemJDBCDao c = new CronometragemJDBCDao();

       if((cronometragem.getCronometrista() == null)||(cronometragem.getGrupo() == null)||(cronometragem.getOperador() == null)||(cronometragem.getProduto() == null)||(cronometragem.getOperacao() == null)||(cronometragem.getTecido() == null)) {
           Toast toast = Toast.makeText( getApplicationContext(),"Preencha todos os campos.", Toast.LENGTH_SHORT);
           toast.show();

        }else{
            //Passa Os dados obtidos pra Activity/tela Cronometria2
            Intent intent = new Intent(this, Cronometria2.class);
            Bundle args = new Bundle();

            args.putSerializable("cronometragem", cronometragem);
            intent.putExtras(args);
            startActivity(intent);
        }


    }

    public void OpenDialog() {
        MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("Sem Conexao. Operar em modo OFF?");
        myDialogFragment.show(getFragmentManager(), "myDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Principal.onOff = false;
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
