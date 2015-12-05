package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;

/**
 * Created by Ruan on 04/12/2015.
 */
public class GrupoSQLite implements GrupoDao {
    SQLiteDatabase database;

    public GrupoSQLite(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public Vector<Grupo> obterGrupos() throws SQLException {
        Vector<Grupo> grupos = new Vector<>();
        String sql = "select idGrupo, descricao from grupo";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Grupo grupo;
        for(int i=0; i < resultado.getCount(); i++){
            grupo= new Grupo(resultado.getInt(0), resultado.getString(1));
            grupos.add(grupo);
            resultado.moveToNext();
        }
        return grupos;
    }

    @Override
    public Grupo obterGrupo(long id) throws SQLException {
        Vector<Grupo> grupos = new Vector<>();
        String sql = "select idGrupo, descricao from grupo where idGrupo = " + id;
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Grupo grupo;
        for(int i=0; i < resultado.getCount(); i++){
            grupo= new Grupo(resultado.getInt(0), resultado.getString(1));
            grupos.add(grupo);
            resultado.moveToNext();
        }
        return grupos.get(0);
    }
}
