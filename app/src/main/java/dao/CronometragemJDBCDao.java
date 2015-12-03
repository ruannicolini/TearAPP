package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Cronometragem;
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
    public int buscaIdParametro(String tipo) throws SQLException {
        return 0;
    }
}
