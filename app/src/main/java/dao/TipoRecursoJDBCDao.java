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
import domain.TipoRecurso;
import util.Conexao;

public class TipoRecursoJDBCDao implements TipoRecursoDao{


	@Override
	public Vector<TipoRecurso> obterTiposRecurso() throws SQLException {
		System.out.println("Entrou!");
		final Vector vetGrupos = new Vector();

		Thread t1 = new Thread(){
			public void run(){
				String sql = "SELECT * FROM tipo_recurso";

				Conexao conexao = FabricaConexao.obterConexao();
				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();

					while (res.next())
					{
						TipoRecurso tr = new TipoRecurso ();
						tr.setIdTipoRecurso(res.getInt("idtipo_recurso"));
						tr.setDescricao(res.getString("descricao"));
						vetGrupos.addElement(tr);
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
		return vetGrupos;
	}

	@Override
	public TipoRecurso obterTipoRecurso(final long id) throws SQLException {
		final Vector vetCronom = new Vector();
		final TipoRecurso tr = new TipoRecurso ();
		Thread t1 = new Thread(){
			public void run(){
				String sql = " SELECT * FROM tipo_recurso WHERE idtipo_recurso  = " + id;
				Log.i("SQL", sql);
				Conexao conexao = FabricaConexao.obterConexao();
				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();
					if (res.next())
					{
						tr.setIdTipoRecurso(res.getInt("idTipoRecurso"));
						tr.setDescricao(res.getString("descricao"));
						vetCronom.addElement(tr);
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
		return tr;
	}



}
