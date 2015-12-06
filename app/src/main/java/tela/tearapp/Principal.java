package tela.tearapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import dao.CronometragemJDBCDao;
import dao.CronometragemSQLite;
import dao.CronometristaSQLite;
import dao.DataBaseCreator;
import dao.FabricaConexao;
import domain.Cronometragem;
import domain.Cronometrista;
import util.Conexao;
import util.NullConnectionException;

public class Principal extends Activity {
    Switch switchAB;
    DataBaseCreator creator;
    static SQLiteDatabase database;
    static Boolean onOff; // Com Conexao Servidor = True;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Criando o banco se necessário
        DataBaseCreator creator = new DataBaseCreator(this);
        database = creator.getWritableDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        //Botão ONOFF
        switchAB = (Switch)menu.findItem(R.id.switchId)
                .getActionView().findViewById(R.id.switchAB);
        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplication(), "Modo ON Ativado", Toast.LENGTH_SHORT).show();
                    Principal.onOff = true;
                } else {
                    Toast.makeText(getApplication(), "Modo OFF Ativado", Toast.LENGTH_SHORT).show();
                    Principal.onOff = false;
                }
            }
        });
        switchAB.setChecked(onOff);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_Sair:
                FecharApp();
                return true;
        }

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void chamaCronometria(View view){
        //Chama Activity Cronometria
        Intent intent = new Intent(this, Cronometria.class);
        startActivity(intent);
    }

    public void chamaConfiguracoes(View view){
        Intent intent = new Intent(this, Configuracoes.class);
        startActivity(intent);

    }

    public void sincronizar(View view) throws SQLException {
        CronometragemJDBCDao cronometragemJDBCDao = new CronometragemJDBCDao();
        CronometragemSQLite cronn = new CronometragemSQLite(database);
        ArrayList<Cronometragem> c = null;

        if(Principal.onOff == true) {
            //Recupera Cronometragens ainda não enviadas;
            try {
                c = new ArrayList<>(cronn.obterCronometragens());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Envia Cronometragens para Servidor
            for (int i = 0; i < c.size(); i++) {
                cronometragemJDBCDao.inserirCronometragem(c.get(i));
            }

            //Sincronização do Banco
            try {
                //Apaga o banco
                this.deleteDatabase("db_cronoanalise");
                // Criando o banco
                DataBaseCreator creator = new DataBaseCreator(this);
                database = creator.getWritableDatabase();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            CronometristaSQLite cronometristaSQLite = new CronometristaSQLite(Principal.database);
            Vector<Cronometrista> cronometristas = cronometristaSQLite.obterCronometristas();
            //cronometristas = cronometristaJDBCDao.obterCronometristas();

            //Mensagem de Confirmação
            Toast toast = Toast.makeText(getApplicationContext(), "Sincronização Finalizada!", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            //Sincronização não será realizada no modo Off
            Toast toast = Toast.makeText(getApplicationContext(), "Modo OFF. Sincronização Não Permitida!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public Boolean testaConexao(){
        final Boolean[] status = {true};
        Thread t1 = new Thread(){
            public void run(){
                String sql = "select * from Cronometrista";
                Conexao conexao = FabricaConexao.obterConexao();
                if(conexao.getDatabaseConnection() == null) {
                    status[0] = false;
                }
            }
        };
        t1.start();
        try {t1.join();} catch (InterruptedException e) {e.printStackTrace();}

        return status[0];
    }

    public void FecharApp(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        //Determina o modo de funcionamento ON or OFF;
        onOff = testaConexao();
    }
}
