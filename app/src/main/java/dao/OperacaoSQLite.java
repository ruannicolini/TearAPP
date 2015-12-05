package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.Operacao;

/**
 * Created by Ruan on 04/12/2015.
 */
public class OperacaoSQLite implements OperacaoDao {
    SQLiteDatabase database;

    public OperacaoSQLite(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public Vector<Operacao> obterOperacoes() throws SQLException {
        Vector<Operacao> operacoes = new Vector<>();
        String sql = "select idoperacao, descricao, idacao, acao, idparte, parte, idfase, fase from operacao";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Operacao op;
        for(int i=0; i < resultado.getCount(); i++){
            op = new Operacao(  resultado.getInt(resultado.getColumnIndex("idoperacao")),
                                resultado.getString(resultado.getColumnIndex("descricao")),
                                resultado.getInt(resultado.getColumnIndex("idacao")),
                                resultado.getString(resultado.getColumnIndex("acao")),
                                resultado.getInt(resultado.getColumnIndex("idparte")),
                                resultado.getString(resultado.getColumnIndex("parte")),
                                resultado.getInt(resultado.getColumnIndex("idfase")),
                                resultado.getString(resultado.getColumnIndex("fase"))
                                );
            operacoes.add(op);
            resultado.moveToNext();
        }
        return operacoes;
    }

    @Override
    public Operacao obterOperacao(long id) throws SQLException {
        return null;
    }
}
