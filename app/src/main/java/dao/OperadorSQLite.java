package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.Operador;

/**
 * Created by Ruan on 04/12/2015.
 */
public class OperadorSQLite implements OperadorDao {
    SQLiteDatabase database;
    GrupoSQLite grupoSQLite;

    public OperadorSQLite(SQLiteDatabase database) {
        this.database = database;
        grupoSQLite = new GrupoSQLite(database);
    }

    @Override
    public Vector<Operador> obterOperadores() throws SQLException {
        Vector<Operador> operadores = new Vector<>();
        String sql = "select idOperador, nome, idGrupo from operador";
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Operador operador;
        for(int i=0; i < resultado.getCount(); i++){
            operador= new Operador(resultado.getInt(0), resultado.getString(1), grupoSQLite.obterGrupo(resultado.getInt(2)));
            operadores.add(operador);
            resultado.moveToNext();
        }
        return operadores;
    }

    @Override
    public Operador obterOperador(long id) throws SQLException {
        return null;
    }

    @Override
    public Vector<Operador> obterOperadoresGrupo(Grupo grupo) throws SQLException {
        Vector<Operador> operadores = new Vector<>();
        String sql = "select idoperador, idgrupo, nome from operador where idgrupo = " + grupo.getIdGrupo();
        Cursor resultado = database.rawQuery(sql, null);
        resultado.moveToFirst();
        Operador operador;
        for(int i=0; i < resultado.getCount(); i++){
            operador= new Operador(resultado.getInt(resultado.getColumnIndex("idoperador")), resultado.getString(resultado.getColumnIndex("nome")), grupoSQLite.obterGrupo(resultado.getInt(resultado.getColumnIndex("idgrupo"))));
            operadores.add(operador);
            resultado.moveToNext();
        }
        return operadores;
    }
}
