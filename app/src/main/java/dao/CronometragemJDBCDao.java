package dao;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometragem;
import domain.Grupo;
import util.Conexao;

/**
 * Created by Ruan on 03/12/2015.
 */
public class CronometragemJDBCDao implements CronometragemDao {

    @Override
    public void inserirCronometragem(final Cronometragem cronometragem) throws SQLException {
        Thread t1 = new Thread(){
            public void run(){
                Conexao conexao = FabricaConexao.obterConexao();
                try{
                    conexao.setAutoCommit(false);
                    String sql = "INSERT INTO cronometragem (idCronometragem," +
                    "ritmo, num_pecas, tolerancia, comprimento_prod, num_ocorrencia," +
                            "idProduto, idCronometrista, idTecido, idOperacao, idOperador, tempo_original, tempo_ideal) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    cronometragem.setIdCronometragem(buscaIdParametro("seqCronometragem"));
                    PreparedStatement pstmt = conexao.prepareStatement(sql);
                    pstmt.setInt(1, cronometragem.getIdCronometragem());
                    pstmt.setInt(2, cronometragem.getRitmo());
                    pstmt.setInt(3, cronometragem.getNumPecas());
                    pstmt.setInt(4, cronometragem.getTolerancia());
                    pstmt.setFloat(5, cronometragem.getComprimentoProduto());
                    pstmt.setInt(6, cronometragem.getNumOcorrencia());
                    pstmt.setInt(7, cronometragem.getProduto().getIdProduto());
                    pstmt.setInt(8, cronometragem.getCronometrista().getIdCronometrista());
                    pstmt.setInt(9, cronometragem.getTecido().getIdTecido());
                    pstmt.setInt(10, cronometragem.getOperacao().getIdOperacao());
                    pstmt.setInt(11, cronometragem.getOperador().getIdOperador());
                    //pstmt.setString(12, null);
                    //pstmt.setString(13, null );

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

    @Override
    public int buscaIdParametro(final String parametroNome) throws SQLException {
        final int id =0;
        final Vector vetId = new Vector();
        Thread t1 = new Thread(){
            public void run(){

                //Busca ID
                String sql = "select * from parametros where parametros.parametro = ?";
                Log.i("SQL", sql);
                Conexao conexao = FabricaConexao.obterConexao();
                PreparedStatement pstmt = null;

                try {
                    pstmt = conexao.prepareStatement(sql);
                    pstmt.setString(1, parametroNome );
                    ResultSet res = pstmt.executeQuery();
                    if (res.next()) {
                        vetId.addElement(  (res.getInt("valor"))    );
                    }

                    //Altera Valor do Parametro pro proximo ID
                    String sqlUpdate =  " UPDATE parametros SET " +
                            " valor = ? " +
                            " WHERE parametro = ?";
                    PreparedStatement pstmtUpdate;

                    pstmtUpdate = conexao.prepareStatement(sqlUpdate);
                    pstmtUpdate.setInt(1, ((int) vetId.get(0))+1 );
                    pstmtUpdate.setString(2, parametroNome );
                    pstmtUpdate.executeUpdate();


                    conexao.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println(e.getMessage());
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
        return (int) vetId.get(0);
    }
}
