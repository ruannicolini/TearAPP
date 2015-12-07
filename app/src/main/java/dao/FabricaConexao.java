/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import android.app.Application;
import android.content.Context;

import tela.tearapp.Principal;
import util.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Giovany
 */
public class FabricaConexao {
    static ConfiguracoesFileDao confDao = new ConfiguracoesFileDao();
    private static Context context;


        public static Conexao obterConexao(){
           DriverInterface driver = new MySQLNativeDriver("balay", "root", "root", "10.0.2.2", "3306");
            //ArrayList<String> conf = confDao.LerConfiguracoes(this);
            //DriverInterface driver = new MySQLNativeDriver(conf.get(1), conf.get(2), conf.get(3), conf.get(0), conf.get(4));
           return driver.obterConexao();
        }

/*
    public static Conexao obterConexao(){
        DriverInterface driver = null;
        ArrayList<String> conf = null;
        try {
            conf = confDao.LerConfiguracoes(context);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new MySQLNativeDriver(conf.get(1), conf.get(2), conf.get(3), conf.get(0), conf.get(4));
        return driver.obterConexao();
    }
 */
}
