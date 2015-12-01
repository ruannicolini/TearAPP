package dao;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Ruan on 29/11/2015.
 */
public class ConfiguracoesFileDao implements ConfiguracoesDao {
    @Override
    public ArrayList<String> LerConfiguracoes(Context context) throws SQLException, IOException {
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
    }

    @Override
    public void gravarConfiguracoes(Context context, String ip, String NomeBanco, String usuarioBanco, String senhaUsuario, String porta) throws SQLException, IOException {

    }
}
