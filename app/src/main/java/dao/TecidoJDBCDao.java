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

import domain.Grupo;
import domain.Tecido;
import util.Conexao;
import util.NullConnectionException;

public class TecidoJDBCDao implements TecidoDao{


	@Override
	public Vector<Tecido> obterTecidos() throws SQLException, NullConnectionException {
		System.out.println("Entrou!");
		final Vector vetGrupos = new Vector();
		final int[] teste = {0};

		Thread t1 = new Thread(){
			public void run(){
				String sql = "select * from Tecido";
				Conexao conexao = FabricaConexao.obterConexao();
				if(conexao.getDatabaseConnection() == null) {
					teste[0] = -1;
				}else {
					PreparedStatement pstmt;
					try {
						pstmt = conexao.prepareStatement(sql);
						ResultSet res = pstmt.executeQuery();

						while (res.next()) {
							Tecido t = new Tecido();
							t.setIdTecido(res.getInt("idTecido"));
							t.setDescricao(res.getString("descricao"));
							vetGrupos.addElement(t);
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
	public Tecido obterTecido(final long id) throws SQLException {
		final Vector vetCronom = new Vector();
		final Tecido  t = new Tecido ();;
		Thread t1 = new Thread(){
			public void run(){
				String sql = " SELECT * FROM Tecido WHERE idTecido  = " + id;
				Log.i("SQL", sql);
				Conexao conexao = FabricaConexao.obterConexao();
				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();
					if (res.next())
					{
						t.setIdTecido(res.getInt("idTecido"));
						t.setDescricao(res.getString("descricao"));
						vetCronom.addElement(t);
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
		return t;
	}



}
