package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import domain.Tecido;
import util.Conexao;
import util.NullConnectionException;

/**
 * Created by Ruan on 07/12/2015.
 */
public class UsuarioJDBCDao implements UsuarioDao {
    @Override
    public Boolean Login(final String username, final String senha) throws SQLException, NullConnectionException {
        System.out.println("Entrou!");
        final Vector vet = new Vector();
        final int[] teste = {0};
        final boolean[] status = {false};

        Thread t1 = new Thread(){
            public void run(){
                String sql = "Select * From Usuario where usuario.username = ? and usuario.senha = ?";
                Conexao conexao = FabricaConexao.obterConexao();
                if(conexao.getDatabaseConnection() == null) {
                    teste[0] = -1;
                }else {
                    PreparedStatement pstmt;
                    try {
                        pstmt = conexao.prepareStatement(sql);
                        pstmt.setString(1, username);
                        pstmt.setString(2, senha);
                        ResultSet res = pstmt.executeQuery();

                        if (res.next()) {
                            String us1 = res.getString("username");
                            String us2 = res.getString("senha");
                            if((us1.equals(username))&&(us2.equals(senha))){
                                status[0] = true;
                            }
                        }

                        /*
                        while (res.next()) {
                            Tecido t = new Tecido();
                            t.setIdTecido(res.getInt("idTecido"));
                            t.setDescricao(res.getString("descricao"));
                            vet.addElement(t);
                        }
                        */
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
        return status[0];
    }
}
