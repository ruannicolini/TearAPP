package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.Produto;

/**
 * Created by Ruan on 04/12/2015.
 */
public class ProdutoSQLite implements ProdutoDao {
    SQLiteDatabase database;

    public ProdutoSQLite(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public Vector<Produto> obterProdutos() throws SQLException {
        Vector<Produto> produtos = new Vector<>();
        String sql = "select idproduto, descricao from produto";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Produto produto;
        for(int i=0; i < resultado.getCount(); i++){
            produto= new Produto(resultado.getInt(0), resultado.getString(1));
            produtos.add(produto);
            resultado.moveToNext();
        }
        return produtos;
    }

    @Override
    public Produto obterProduto(long id) throws SQLException {
        return null;
    }
}
