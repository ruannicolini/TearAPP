/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import util.*;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Giovany
 */
public class FabricaConexao {

    public static Conexao obterConexao() {
       DriverInterface driver = new MySQLNativeDriver("balay", "root", "root", "10.0.2.2", "3306");
        //ArrayList<String> conf = confDao.LerConfiguracoes(this);
        //DriverInterface driver = new MySQLNativeDriver(conf.get(1), conf.get(2), conf.get(3), conf.get(0), conf.get(4));
       return driver.obterConexao();
    }
}
