package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.TipoRecurso;

/**
 * Created by Ruan on 05/12/2015.
 */
public class TipoRecursoSQLite implements TipoRecursoDao {
    SQLiteDatabase database;

    public TipoRecursoSQLite(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public Vector<TipoRecurso> obterTiposRecurso() throws SQLException {
        Vector<TipoRecurso> recursos = new Vector<>();
        String sql = "select idtipo_recurso, descricao from tipo_Recurso";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        TipoRecurso rec;
        for(int i=0; i < resultado.getCount(); i++){
            rec= new TipoRecurso(resultado.getInt(0), resultado.getString(1));
            recursos.add(rec);
            resultado.moveToNext();
        }
        return recursos;
    }

    @Override
    public TipoRecurso obterTipoRecurso(long id) throws SQLException {
        Vector<TipoRecurso> recursos = new Vector<>();
        String sql = "select idtipo_recurso, descricao from tipo_Recurso where idtipo_recurso =" +id;
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        TipoRecurso rec;
        for(int i=0; i < resultado.getCount(); i++){
            rec= new TipoRecurso(resultado.getInt(0), resultado.getString(1));
            recursos.add(rec);
            resultado.moveToNext();
        }
        return recursos.get(0);
    }
}
