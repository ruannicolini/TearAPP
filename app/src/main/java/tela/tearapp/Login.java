package tela.tearapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import dao.CronometragemSQLite;
import dao.FabricaConexao;
import dao.UsuarioJDBCDao;
import util.Conexao;
import util.NullConnectionException;

public class Login extends Activity {
    private String login;
    private String senha;
    UsuarioJDBCDao usuarioJDBCDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioJDBCDao = new UsuarioJDBCDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void logar(View view) {
        EditText etLogin = (EditText) findViewById(R.id.EditLogin);
        setLogin(etLogin.getText().toString());

        EditText etSenha = (EditText) findViewById(R.id.EditSenha);
        setSenha(etSenha.getText().toString());


        //Tirar depois
        //Intent intent = new Intent(this, Principal.class);
        //startActivity(intent);

        //Chama Activity Principal
        if (veirificaLogin(login, senha) == true) {

            //Chama Activity Principal
            Intent inte = new Intent(this, Principal.class);
            startActivity(inte);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.erroLogin), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public boolean veirificaLogin(String login, String senha){
        Boolean status = false;
        try {
            status =  usuarioJDBCDao.Login(login, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullConnectionException e) {
            e.printStackTrace();
        }

        return status;
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
}
