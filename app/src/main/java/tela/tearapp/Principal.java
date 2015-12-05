package tela.tearapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Vector;

import dao.CronometristaSQLite;
import dao.DataBaseCreator;
import domain.Cronometrista;

public class Principal extends Activity {
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
        try {
            //Apaga o banco
            this.deleteDatabase("db_cronoanalise");
            // Criando o banco
            DataBaseCreator creator = new DataBaseCreator(this);
            database = creator.getWritableDatabase();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        CronometristaSQLite cronometristaSQLite = new CronometristaSQLite(Principal.database);
        Vector<Cronometrista> cronometristas = cronometristaSQLite.obterCronometristas();
        //cronometristas = cronometristaJDBCDao.obterCronometristas();

        //Mensagem de Confirmação
        Toast toast = Toast.makeText(getApplicationContext(), "Sincronização Finalizada!", Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void onDestroy(){
        super.onDestroy();
        database.close();
    }
}
