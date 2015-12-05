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

import dao.CronometragemSQLite;

public class Login extends Activity {
    private String login;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    public void showDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);
        builder.setMessage(message);

        //Botão1
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        //Botão2
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        builder.show();
    }

    public void logar(View view) {
        EditText etLogin = (EditText) findViewById(R.id.EditLogin);
        setLogin(etLogin.getText().toString());

        EditText etSenha = (EditText) findViewById(R.id.EditSenha);
        setSenha(etSenha.getText().toString());


        //Tirar depois

        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);

        //Chama Activity Principal
        if (veirificaLogin(login, senha) == true) {
            Context contexto = getApplicationContext();
            String texto = "Logou!!";
            int duracao = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contexto, texto, duracao);
            toast.show();

            //Chama Activity Principal
            Intent inte = new Intent(this, Principal.class);

            startActivity(inte);

        } else {
            Context contexto = getApplicationContext();
            String texto = getString(R.string.erroLogin);
            int duracao = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(contexto, texto, duracao);
            toast.show();

        }
    }

    public boolean veirificaLogin(String login, String senha){
        if(login.equals("r") && senha.equals("r")){
            return true;
        }else {
            return false;
        }
    }
}
