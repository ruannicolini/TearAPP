/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;


import domain.Cronometrista;
import util.*;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.*;

import android.util.Log;

public class CronometristaJDBCDao implements CronometristaDao {


     public Vector<Cronometrista> obterCronometristas() throws SQLException, NullConnectionException{
		 System.out.println("Entrou!");
		 final Vector vetCronom = new Vector();
		 final int[] teste = {0};

		 Thread t1 = new Thread(){
			 public void run(){
				 String sql = "select * from Cronometrista";
				 Conexao conexao = FabricaConexao.obterConexao();
				 if(conexao.getDatabaseConnection() == null) {
					 teste[0] = -1;
				 }else {
					 PreparedStatement pstmt;
					 try {
						 pstmt = conexao.prepareStatement(sql);
						 ResultSet res = pstmt.executeQuery();

						 while (res.next()) {
							 Cronometrista c = new Cronometrista();
							 c.setIdCronometrista(res.getInt("idCronometrista"));
							 c.setNome(res.getString("nome"));
							 vetCronom.addElement(c);
						 }
						 conexao.close();
					 } catch (SQLException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 } catch (Exception e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }
				 }
			 }
		 };

		 t1.start();

		 try {
			 t1.join();
			 if(teste[0] == -1){throw new NullConnectionException("Sem Conexão","APP não consegue acessar servidor.");}
		 } catch (InterruptedException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 return vetCronom;
	 }

    public Cronometrista obterCronometrista(final long id) {
    	final Vector vetCronom = new Vector();
        final Cronometrista c = new Cronometrista();
        Thread t1 = new Thread(){
			public void run(){
				 String sql = " SELECT * FROM Cronometrista WHERE idCronometrista = " + id;
		         Log.i("SQL", sql); 
		         Conexao conexao = FabricaConexao.obterConexao();
		         
		         
		         PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();

			         
			        if (res.next())
			        {
			            c.setIdCronometrista(res.getInt("idCronometrista"));
			            c.setNome(res.getString("nome"));
			            vetCronom.addElement(c);
			        }
			         conexao.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
			}
        };
        t1.start();
        try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return c;
    }
}
