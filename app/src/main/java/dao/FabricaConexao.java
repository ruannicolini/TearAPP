/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import util.*;

import java.sql.*;

/**
 *
 * @author Giovany
 */
public class FabricaConexao {
    public static Conexao obterConexao() {
       DriverInterface driver = new MySQLNativeDriver("balay", "root", "root", "10.0.2.2", "3306");
       return driver.obterConexao(); 
    }
}
