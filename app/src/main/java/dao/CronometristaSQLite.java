package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import domain.Cronometrista;

/**
 * Created by Ruan on 04/12/2015.
 */
public class CronometristaSQLite implements CronometristaDao {
    SQLiteDatabase database;

    public CronometristaSQLite(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public Vector<Cronometrista> obterCronometristas() throws SQLException {
        Vector<Cronometrista> cronometristas = new Vector<>();
        //LinkedList ls = new LinkedList();
        String sql = "select idcronometrista, nome from cronometrista";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Cronometrista cro;
        for(int i=0; i < resultado.getCount(); i++){
            cro= new Cronometrista(resultado.getInt(0), resultado.getString(1));
            //ls.add(cro);
            cronometristas.add(cro);
            resultado.moveToNext();
        }

        return cronometristas;
    }

    @Override
    public Cronometrista obterCronometrista(long id) throws SQLException {
        return null;
    }
}
