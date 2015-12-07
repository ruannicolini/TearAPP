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
           return driver.obterConexao();
        }

        public static Conexao obterConexao(String nomeBD, String userBD, String senhaBD, String ipServidor, String porta){
            DriverInterface driver = new MySQLNativeDriver(nomeBD, userBD, senhaBD, ipServidor, porta);
            //DriverInterface driver = new MySQLNativeDriver(conf.get(1), conf.get(2), conf.get(3), conf.get(0), conf.get(4));
            return driver.obterConexao();
        }
}
