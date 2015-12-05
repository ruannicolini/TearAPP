package dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.Batida;
import domain.Cronometragem;
import domain.TipoRecurso;

/**
 * Created by Ruan on 05/12/2015.
 */
public class CronometragemSQLite implements CronometragemDao {
    SQLiteDatabase database;
    CronometristaSQLite cronometristaSQLite;
    OperadorSQLite operadorSQLite;
    OperacaoSQLite operacaoSQLite;
    TecidoSQLite tecidoSQLite;
    ProdutoSQLite produtoSQLite;

    public CronometragemSQLite(SQLiteDatabase database) {
        this.database = database;
        cronometristaSQLite = new CronometristaSQLite(database);
        operadorSQLite = new OperadorSQLite(database);
        operacaoSQLite = new OperacaoSQLite(database);
        tecidoSQLite = new TecidoSQLite(database);
        produtoSQLite = new ProdutoSQLite(database);
    }

    @Override
    public void inserirCronometragem(Cronometragem cronometragem) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("ritmo", cronometragem.getRitmo());
        values.put("num_pecas", cronometragem.getNumPecas());
        values.put("tolerancia", cronometragem.getTolerancia());
        values.put("comprimento_prod", cronometragem.getComprimentoProduto());
        values.put("num_ocorrencia", cronometragem.getNumOcorrencia());
        values.put("idProduto", cronometragem.getProduto().getIdProduto());
        values.put("idCronometrista", cronometragem.getCronometrista().getIdCronometrista());
        values.put("idTecido", cronometragem.getTecido().getIdTecido());
        values.put("idOperador", cronometragem.getOperador().getIdOperador());
        values.put("idOperacao", cronometragem.getOperacao().getIdOperacao());
        long id = database.insert("cronometragem", null, values);
        cronometragem.setIdCronometragem( (int) id);
        inserirArrayTipoRecurso(cronometragem);
        inserirArrayBatidas(cronometragem);
    }

    @Override
    public void inserirCronometragem_TipoRecurso(Cronometragem cronometragem, TipoRecurso recurso) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("idcronometragem", cronometragem.getIdCronometragem());
        values.put("idtipo_recurso", recurso.getIdTipoRecurso());
        database.insert("cronometragem_has_tipo_Recurso", null, values);
    }

    @Override
    public void inserirArrayTipoRecurso(Cronometragem cronometragem) throws SQLException {
        for(int i = 0; i < cronometragem.getRecursos().size(); i++){
            inserirCronometragem_TipoRecurso(cronometragem, cronometragem.getRecursos().get(i));
        }
    }

    @Override
    public void inserirBatida(Batida batida) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("minutos", batida.getMinutos());
        values.put("segundos", batida.getSegundos());
        values.put("centesimos", batida.getCentezimos());
        values.put("utilizar", batida.isUtilizar());
        values.put("idcronometragem", batida.getCronometragem().getIdCronometragem());
        database.insert("batida", null, values);
    }

    @Override
    public void inserirArrayBatidas(Cronometragem cronometragem) throws SQLException {
        for(int i = 0; i < cronometragem.getBatidas().size(); i++) {
            cronometragem.getBatidas().get(i).setCronometragem(cronometragem);
            inserirBatida(cronometragem.getBatidas().get(i));
        }
    }

    public Vector<Cronometragem> obterCronometragens() throws SQLException {
        Vector<Cronometragem> cronometragens = new Vector<>();
        //LinkedList ls = new LinkedList();
        String sql = "select idcronometragem, ritmo, num_pecas,tolerancia, comprimento_prod, num_ocorrencia," +
                "idProduto, idCronometrista, idTecido,  idOperador, idOperacao, referencia from Cronometragem";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Cronometragem cro;
        for(int i=0; i < resultado.getCount(); i++){
            cro= new Cronometragem(resultado.getInt(0),
                    resultado.getInt(1),
                    resultado.getInt(2),
                    resultado.getInt(3),
                    resultado.getFloat(4),
                    resultado.getInt(5),
                    produtoSQLite.obterProduto(resultado.getInt(6)),
                    cronometristaSQLite.obterCronometrista(resultado.getInt(7)),
                    tecidoSQLite.obterTecido(resultado.getInt(8)),
                    operadorSQLite.obterOperador(resultado.getInt(9)),
                    operacaoSQLite.obterOperacao(resultado.getInt(10)),
                    resultado.getString(11)
                    );
            //Atribui Batidas

            //Atribui Recursos


            cronometragens.add(cro);
            resultado.moveToNext();
        }

        return cronometragens;
    }


}
