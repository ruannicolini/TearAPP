/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.*;

import android.util.Log;





/**
 *
 * @author Giovany
 */
public class MySQLNativeDriver extends SQLDriver implements DriverInterface{
    private String ip, porta;
    
    public MySQLNativeDriver(String nomeBanco, String usuario, String senha, String ip, String porta){
        super(nomeBanco, usuario, senha);
        this.ip = ip;
        this.porta = porta;
    }
    
    public MySQLNativeDriver(String nomeBanco, String usuario, String senha){
        super(nomeBanco, usuario, senha);
        this.ip = "localhost"; //
        this.porta = "3306";        
    }
    
    public Conexao obterConexao() {
        try {
            String url; 
            url = "jdbc:mysql://" + this.ip + ":" + this.porta + "/" + this.nomeBanco;
            Class.forName("com.mysql.jdbc.Driver");
            Conexao conexao = new Conexao();
            Log.i("MYSQL", "Tentando conectar ..."); 
            conexao.setConnection(DriverManager.getConnection(url, this.usuario, this.senha));
            Log.i("MYSQL", "Conectado."); 
            return conexao;
        }catch(ClassNotFoundException erro){
            Log.e("MYSQL","Erro: "+erro); 
        }catch(SQLException erro){
        	Log.e("MYSQL","Erro: "+erro); 
        }
		return null; 
    }
}
