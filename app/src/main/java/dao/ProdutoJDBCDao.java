/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;


import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometrista;
import domain.Produto;
import util.Conexao;

public class ProdutoJDBCDao implements ProdutoDao {


     public Vector<Produto> obterProdutos() {
		 System.out.println("Entrou!");
		 final Vector vetCronom = new Vector();

		 Thread t1 = new Thread(){
			 public void run(){
				 String sql = "select * from Produto";
				 Conexao conexao = FabricaConexao.obterConexao();
				 PreparedStatement pstmt;
				 try {
					 pstmt = conexao.prepareStatement(sql);
					 ResultSet res = pstmt.executeQuery();

					 while (res.next()) {
						 Produto p = new Produto();
						 p.setIdProduto(res.getInt("idProduto"));
						 p.setDescricao(res.getString("descricao"));
						 vetCronom.addElement(p);
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

    public Produto obterProduto(final long id) {
    	final Vector vetCronom = new Vector();
        final Produto p = new Produto();
        Thread t1 = new Thread(){
			public void run(){
				 String sql = " SELECT * FROM Produto WHERE idProduto = " + id;
		         Log.i("SQL", sql); 
		         Conexao conexao = FabricaConexao.obterConexao();
		         
		         
		         PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();
					if (res.next()) {
			            p.setIdProduto(res.getInt("idProduto"));
						p.setDescricao(res.getString("descricao"));
			            vetCronom.addElement(p);
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
        return p;
    }
}
