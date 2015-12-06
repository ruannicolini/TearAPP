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
import domain.Grupo;
import util.Conexao;
import util.NullConnectionException;

public class GrupoJDBCDao implements GrupoDao{


	@Override
	public Vector<Grupo> obterGrupos() throws SQLException, NullConnectionException {
		System.out.println("Entrou!");
		final Vector vetGrupos = new Vector();
		final int[] teste = {0};

		Thread t1 = new Thread(){
			public void run(){
				String sql = "select * from Grupo";
				Conexao conexao = FabricaConexao.obterConexao();
				if(conexao.getDatabaseConnection() == null) {
					teste[0] = -1;
				}else {
					PreparedStatement pstmt;
					try {
						pstmt = conexao.prepareStatement(sql);
						ResultSet res = pstmt.executeQuery();

						while (res.next()) {
							Grupo g = new Grupo();
							g.setCod(res.getInt("idGrupo"));
							g.setDescricao(res.getString("descricao"));
							vetGrupos.addElement(g);
						}
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
			if(teste[0] == -1){throw new NullConnectionException("Sem Conexão","APP não consegue acessar servidor.");}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vetGrupos;
	}

	@Override
	public Grupo obterGrupo(final long id) throws SQLException {
		final Vector vetCronom = new Vector();
		final Grupo  g = new Grupo ();;
		Thread t1 = new Thread(){
			public void run(){
				String sql = " SELECT * FROM Grupo WHERE idGrupo  = " + id;
				Log.i("SQL", sql);
				Conexao conexao = FabricaConexao.obterConexao();
				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();
					if (res.next())
					{
						g.setCod(res.getInt("idGrupo"));
						g.setDescricao(res.getString("descricao"));
						vetCronom.addElement(g);
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
		return g;
	}



}
