package tela.tearapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import domain.Cronometragem;
import domain.Cronometrista;

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

        onOff = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        switchAB = (Switch)menu.findItem(R.id.switchId)
                .getActionView().findViewById(R.id.switchAB);

        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplication(), "ON", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getApplication(), "OFF", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
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

        }
    }

    protected void onDestroy(){
        super.onDestroy();
        database.close();
    }
}
