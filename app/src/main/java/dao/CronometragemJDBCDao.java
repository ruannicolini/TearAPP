package dao;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import domain.Batida;
import domain.Cronometragem;
import domain.Grupo;
import domain.TipoRecurso;
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
                    //Inseri Na tabela Cronometragem
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
                    pstmt.setInt(12, 0);
                    pstmt.setInt(13, 0);

                    pstmt.execute();
                    inserirArrayTipoRecurso(cronometragem);
                    inserirArrayBatidas(cronometragem);
                    conexao.commit();
                }catch (SQLException erro) {
                    try {
                        conexao.rollback();
                        throw erro;
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }finally{
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
    public void inserirCronometragem_TipoRecurso(final Cronometragem cronometragem, final TipoRecurso recurso) throws SQLException {

            Thread t1 = new Thread(){
                public void run(){
                    Conexao conexao = FabricaConexao.obterConexao();
                    try{
                        //Inseri Na tabela Cronometragem_has_Tipo_recurso
                        conexao.setAutoCommit(false);
                        String sql = "insert into cronometragem_has_tipo_recurso (idCronometragem, idTipoRecurso)values(?,?)";

                        PreparedStatement pstmt = conexao.prepareStatement(sql);
                        pstmt.setInt(1, cronometragem.getIdCronometragem());
                        pstmt.setInt(2, recurso.getIdTipoRecurso());

                        pstmt.execute();
                        conexao.commit();
                    }catch (SQLException erro) {
                        try {
                            conexao.rollback();
                            throw erro;
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }finally{
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
    public void inserirArrayTipoRecurso(Cronometragem cronometragem) throws SQLException {
        for(int i = 0; i < cronometragem.getRecursos().size(); i++){
            inserirCronometragem_TipoRecurso(cronometragem, cronometragem.getRecursos().get(i));
        }
    }

    @Override
    public void inserirBatida(final Batida batida) throws SQLException {
        Thread t1 = new Thread(){
            public void run(){
                Conexao conexao = FabricaConexao.obterConexao();
                try{
                    //Inseri Na tabela Cronometragem_has_Tipo_recurso
                    conexao.setAutoCommit(false);
                    String sql = "insert into Batida (idbatida, idCronometragem, minutos, segundos, centezimos, utilizar)values(?,?,?,?,?,?);";

                    PreparedStatement pstmt = conexao.prepareStatement(sql);
                    batida.setIdBatida(buscaIdParametro("seqBatida"));
                    pstmt.setInt(1, batida.getIdBatida());
                    pstmt.setInt(2, batida.getCronometragem().getIdCronometragem());
                    pstmt.setInt(3, batida.getMinutos());
                    pstmt.setInt(4, batida.getSegundos());
                    pstmt.setInt(5, batida.getCentezimos());
                    pstmt.setBoolean(6, batida.isUtilizar());

                    pstmt.execute();
                    conexao.commit();
                }catch (SQLException erro) {
                    try {
                        conexao.rollback();
                        throw erro;
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }finally{
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
    public void inserirArrayBatidas(Cronometragem cronometragem) throws SQLException {
        for(int i = 0; i < cronometragem.getBatidas().size(); i++) {
            cronometragem.getBatidas().get(i).setCronometragem(cronometragem);
            inserirBatida(cronometragem.getBatidas().get(i));
        }

    }

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
