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


    public void inserirCronometrista(final Cronometrista c) throws SQLException{
    	Thread t1 = new Thread(){
			public void run(){
			       Conexao conexao = FabricaConexao.obterConexao(); 
			        try{
			            conexao.setAutoCommit(false);

			            String sql = "INSERT INTO Cronometrista (idCronometrista, nome) values ( ?, ?)";
			            PreparedStatement pstmt = conexao.prepareStatement(sql);
			            pstmt.setInt(1, c.getIdCronometrista());
			            pstmt.setString(2, c.getNome());
			            pstmt.executeUpdate();
			            conexao.commit();
			        }
			        catch (SQLException erro) {
			            try {
							conexao.rollback();
							throw erro;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			        finally{
			            try {
							conexao.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
 
    }

    public void alterarCronometrista(final Cronometrista c) throws  SQLException{
    	Thread t1 = new Thread(){
			public void run(){
				Conexao conexao = FabricaConexao.obterConexao(); 
		        try {
					conexao.setAutoCommit(true);
					String sql =  " UPDATE Cronometrista SET " +
		                      " nome= ? " +
		                      " WHERE idCronometrista = ?";
		            PreparedStatement pstmt;

					pstmt = conexao.prepareStatement(sql);
			        pstmt.setString(1, c.getNome() );
			        pstmt.setInt(2, c.getIdCronometrista());
			        pstmt.executeUpdate(); 
			        conexao.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
        
    }
    
    public void excluirCronometrista(final Cronometrista c) {
    	Thread t1 = new Thread(){
			public void run(){
		        Conexao conexao = FabricaConexao.obterConexao(); 
		        try {
					conexao.setAutoCommit(true);
			        String sql = " DELETE FROM Cronometrista WHERE idCronometrista = ?" ;
			        PreparedStatement pstmt = conexao.prepareStatement(sql);   
			        pstmt.setInt(1, c.getIdCronometrista() );
			        pstmt.executeUpdate();   
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

    }
    
    
	 public Vector<Cronometrista> obterCronometristas() {
		 return this.obterCronometristasCriterio(null, null, null, true);
	 }
	 
	 public Vector<Cronometrista> obterCronometristasPorNomeOrdem(String nome, boolean crescente) {
		 return this.obterCronometristasCriterio("nome", "'%" + nome + "%'", " LIKE ", crescente);
	 }
    
    private Vector<Cronometrista> obterCronometristasCriterio(final String coluna, final String valor, final String operacao, final boolean crescente)  {
        final Vector vetCronom = new Vector();
        
        Thread t1 = new Thread(){
			public void run(){
		    	String sql;
				String complemento;
		        
		        if (crescente)
					 complemento = " ASC ";
				 else
					 complemento = " DESC ";
				 if (coluna == null){
					 sql = "select idCronometrista, nome from Cronometrista";
				 } else {
					 sql = "select idCronometrista, nome from Cronometrista where " + coluna + operacao + valor + " order by " + coluna + complemento;
				 }

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
