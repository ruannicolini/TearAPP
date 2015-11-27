/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;


import domain.Cronometrista;
import util.*;

import java.sql.*;
import java.util.*;

import android.util.Log;

public class CronometristaJDBCDao implements CronometristaDao {


     public Vector<Cronometrista> obterCronometristas() {
		 System.out.println("Entrou!");
		 final Vector vetCronom = new Vector();


		 Thread t1 = new Thread(){
			 public void run(){
				 String sql = "select * from Cronometrista";

				 Conexao conexao = FabricaConexao.obterConexao();
				 PreparedStatement pstmt;
				 try {
					 pstmt = conexao.prepareStatement(sql);
					 ResultSet res = pstmt.executeQuery();

					 while (res.next())
					 {
						 Cronometrista c = new Cronometrista();
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


		 return vetCronom;
	 }

    public Cronometrista obterCronometrista(final long id) {
    	final Vector vetCronom = new Vector();
        final Cronometrista c = new Cronometrista();;
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
