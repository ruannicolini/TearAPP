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
       DriverInterface driver = new MySQLNativeDriver("u739430712_tear", "u739430712_user", "@Rn102313", "mysql.hostinger.com.br", "3306");
       return driver.obterConexao(); 
    }
}
