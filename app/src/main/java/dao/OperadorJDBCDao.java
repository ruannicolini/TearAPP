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
import domain.Operador;
import util.Conexao;

public class OperadorJDBCDao implements OperadorDao{
	GrupoJDBCDao grupoJDBC = new GrupoJDBCDao();

	@Override
	public Vector<Operador> obterOperadores() throws SQLException {
		final Vector vetGrupos = new Vector();
		Thread t1 = new Thread(){
			public void run(){
				String sql = "select * from Operador";

				Conexao conexao = FabricaConexao.obterConexao();
				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();

					while (res.next())
					{
						Operador o = new Operador ();
						o.setIdOperador((res.getInt("idOperador")));
						o.setNome(res.getString("nome"));
						o.setGrupo(grupoJDBC.obterGrupo(res.getInt("idGrupo")));

						vetGrupos.addElement(o);
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
	public Operador obterOperador(final long id) throws SQLException {
		final Vector vetCronom = new Vector();
		final Operador o = new Operador ();
		Thread t1 = new Thread(){
			public void run(){
				String sql = " SELECT * FROM Operador WHERE idOperador  = " + id;
				Log.i("SQL", sql);
				Conexao conexao = FabricaConexao.obterConexao();

				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();
					if (res.next()) {
						o.setIdOperador(res.getInt("idOperador"));
						o.setNome(res.getString("nome"));
						o.setGrupo( grupoJDBC.obterGrupo(res.getInt("idGrupo")));
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

	@Override
	public Vector<Operador> obterOperadoresGrupo(final Grupo grupo) throws SQLException {
		final Vector vetGrupos = new Vector();
		Thread t1 = new Thread(){
			public void run(){
				String sql = "select * from Operador where idGrupo = " + grupo.getIdGrupo();

				Conexao conexao = FabricaConexao.obterConexao();
				PreparedStatement pstmt;
				try {
					pstmt = conexao.prepareStatement(sql);
					ResultSet res = pstmt.executeQuery();

					while (res.next())
					{
						Operador o = new Operador ();
						o.setIdOperador(res.getInt("idOperador"));
						o.setNome(res.getString("nome"));
						o.setGrupo(grupoJDBC.obterGrupo(res.getInt("idGrupo")));

						vetGrupos.addElement(o);
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


}
