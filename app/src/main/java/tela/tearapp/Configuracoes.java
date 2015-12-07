package tela.tearapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        if(lista != null) {
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
        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        EditText editBdNome = (EditText) findViewById(R.id.EditBDNome);
        EditText editIpServidor = (EditText) findViewById(R.id.EditIpServidor);
        EditText editBdUser = (EditText) findViewById(R.id.EditBDUser);
        EditText editBdSenha = (EditText) findViewById(R.id.EditBDSenha);
        EditText editPorta = (EditText) findViewById(R.id.EditPorta);

        if(btnSalvar.getText().equals("ALTERAR")){
            btnSalvar.setText("SALVAR");
            editBdNome.setEnabled(true);
            editIpServidor.setEnabled(true);
            editBdUser.setEnabled(true);
            editBdSenha.setEnabled(true);
            editPorta.setEnabled(true);
        }else {
            String text1 = editIpServidor.getText().toString();
            String text2 = editBdNome.getText().toString();
            String text3 = editBdUser.getText().toString();
            String text4 = editBdSenha.getText().toString();
            String text5 = editPorta.getText().toString();
            try {
                confDao.gravarConfiguracoes(this, text1,text2 ,text3 ,text4 ,text5 );
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            editBdNome.setEnabled(false);
            editIpServidor.setEnabled(false);
            editBdUser.setEnabled(false);
            editBdSenha.setEnabled(false);
            editPorta.setEnabled(false);
            btnSalvar.setText("ALTERAR");
        }
    }
}
