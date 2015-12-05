package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.Tecido;

/**
 * Created by Ruan on 04/12/2015.
 */
public class TecidoSQLite implements TecidoDao {
    SQLiteDatabase database;

    public TecidoSQLite(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public Vector<Tecido> obterTecidos() throws SQLException {
        Vector<Tecido> tecidos = new Vector<>();
        String sql = "select idTecido, descricao from tecido";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Tecido tecido;
        for(int i=0; i < resultado.getCount(); i++){
            tecido= new Tecido(resultado.getInt(0), resultado.getString(1));
            tecidos.add(tecido);
            resultado.moveToNext();
        }
        return tecidos;
    }

    @Override
    public Tecido obterTecido(long id) throws SQLException {
        Vector<Tecido> tecidos = new Vector<>();
        String sql = "select idTecido, descricao from tecido where idTecido = " + id;
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Tecido tecido;
        for(int i=0; i < resultado.getCount(); i++){
            tecido= new Tecido(resultado.getInt(0), resultado.getString(1));
            tecidos.add(tecido);
            resultado.moveToNext();
        }
        return tecidos.get(0);
    }
}
