package dao;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Created by Ruan on 29/11/2015.
 */
public class ConfiguracoesFileDao implements ConfiguracoesDao {
    public ConfiguracoesFileDao() {
    }

    @Override
    public ArrayList<String> LerConfiguracoes(Context context) throws SQLException, IOException {
        /*
        //Arquivo Assets não é alterado.
        AssetManager assetManager = context.getResources().getAssets();
        InputStream inputStream;
        ArrayList<String> linhas = null;
        inputStream = assetManager.open("conf.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String recebe_string;
        linhas = new ArrayList<>();
        while((recebe_string = bufferedReader.readLine())!=null){
            linhas.add(recebe_string);
        }
        inputStream.close();
        return linhas;
        */

        ArrayList<String> lista = new ArrayList<>();
        File textfile = new File(context.getExternalFilesDir(null), "TearAPPconfiguracoes.txt");
        if (textfile.exists()){
            ArrayList<String> linhas = null;
            FileInputStream input = new FileInputStream(textfile);
            byte[] buffer = new byte[(int) textfile.length()];

            input.read(buffer);
            String texto = new String(buffer);

            String[] t = texto.split(Pattern.quote(";"));
            for(int i =0 ; i < t.length; i++){
                lista.add(t[i]);
            }
        }


        return lista;
    }

    @Override
    public Boolean gravarConfiguracoes(Context context, String ip, String nomeBanco, String usuarioBanco, String senhaUsuario, String porta) throws SQLException, IOException {

        try {
            File file = new File(context.getExternalFilesDir(null),"TearAPPconfiguracoes.txt");
            FileOutputStream out = new FileOutputStream(file, true);
            String text = ip +";" + nomeBanco +";"+usuarioBanco+";"+senhaUsuario+";"+porta+";";

            out.write(text.getBytes());
            out.flush();
            out.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
