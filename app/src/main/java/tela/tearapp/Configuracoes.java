package tela.tearapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConfiguracoesFileDao;

public class Configuracoes extends Activity {

    ConfiguracoesFileDao confDao;
    EditText editTextIP, editTextNomeBD, editTextUserBD, editTextSenhaBD, editTextPorta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        ArrayList<String> lista = null;
        confDao = new ConfiguracoesFileDao();

        try {
            lista = confDao.LerConfiguracoes(this);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Ip Server
        editTextIP = (EditText) findViewById(R.id.EditIpServidor);
        editTextIP.setText(lista.get(0));

        // BD Name
        editTextNomeBD = (EditText) findViewById(R.id.EditBDNome);
        editTextNomeBD.setText(lista.get(1));

        //BD User
        editTextUserBD = (EditText) findViewById(R.id.EditBDUser);
        editTextUserBD.setText(lista.get(2));

        //BD Senha
        editTextSenhaBD = (EditText) findViewById(R.id.EditBDSenha);
        editTextSenhaBD.setText(lista.get(3));

        //Porta
        editTextPorta = (EditText) findViewById(R.id.EditPorta);
        editTextPorta.setText(lista.get(4));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracoes, menu);
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

    public void cancelar(View view){
        finish();
    }

    public void salvar(View view) {
        try {
            confDao.gravarConfiguracoes(this, editTextIP.getText().toString(), editTextNomeBD.getText().toString(),
                    editTextUserBD.getText().toString(), editTextSenhaBD.getText().toString(), editTextPorta.getText().toString());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
