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
import domain.Operacao;
import util.Conexao;

public class OperacaoJDBCDao implements OperacaoDao {


     public Vector<Operacao> obterOperacoes() {
		 System.out.println("Entrou!");
		 final Vector vetCronom = new Vector();
		 Thread t1 = new Thread(){
			 public void run(){
				 String sql = " SELECT op.*, \n" +
						 "ac.descricao as descricaoAcao,\n" +
						 "pt.descricao as descricaoParte,\n" +
						 "fs.descricao as descricaoFase\n" +
						 "FROM Operacao op\n" +
						 "left outer join acao ac on op.idacao = ac.idacao\n" +
						 "left outer join parte pt on op.idParte = pt.idParte\n" +
						 "left outer join fase fs on op.idFase = fs.idfase";

				 Conexao conexao = FabricaConexao.obterConexao();
				 PreparedStatement pstmt;
				 try {
					 pstmt = conexao.prepareStatement(sql);
					 ResultSet res = pstmt.executeQuery();

					 while (res.next()) {
						 Operacao o = new Operacao ();
						 o.setIdOperacao(res.getInt("idOperacao"));
						 o.setDescricao(res.getString("descricao"));

						 //Acao
						 o.setIdAcao(res.getInt("idAcao"));
						 o.setAcao(res.getString("descricaoAcao"));

						 //Parte
						 o.setIdParte(res.getInt("idParte"));
						 o.setParte(res.getString("descricaoParte"));

						 //Fase
						 o.setIdFase(res.getInt("idFase"));
						 o.setFase(res.getString("descricaoFase"));

						 vetCronom.addElement(o);
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

    public Operacao obterOperacao(final long id) {
    	final Vector vetCronom = new Vector();
        final Operacao o = new Operacao();
        Thread t1 = new Thread(){
			public void run(){
				 String sql = " SELECT op.*, \n" +
						 "ac.descricao as descricaoAcao,\n" +
						 "pt.descricao as descricaoParte,\n" +
						 "fs.descricao as descricaoFase\n" +
						 "FROM Operacao op\n" +
						 "left outer join acao ac on op.idacao = ac.idacao\n" +
						 "left outer join parte pt on op.idParte = pt.idParte\n" +
						 "left outer join fase fs on op.idFase = fs.idfase\n" +
						 "where idOperacao =  " + id;
		         Log.i("SQL", sql);
				Conexao conexao = FabricaConexao.obterConexao();
		         PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();
					if (res.next()) {
						o.setIdOperacao(res.getInt("idOperacao"));
						o.setDescricao(res.getString("descricao"));

						//Acao
			            o.setIdAcao(res.getInt("idAcao"));
						o.setAcao(res.getString("descricaoAcao"));

						//Parte
						o.setIdParte(res.getInt("idParte"));
						o.setParte(res.getString("descricaoParte"));

						//Fase
						o.setIdFase(res.getInt("idFase"));
						o.setFase(res.getString("descricaoFase"));

			            vetCronom.addElement(o);
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
        return o;
    }
}
