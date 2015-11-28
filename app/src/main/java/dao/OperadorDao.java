/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.Operador;


public interface OperadorDao {
    public Vector<Operador> obterOperadores() throws SQLException;
    public Operador obterOperador(final long id) throws SQLException;
    public Vector<Operador> obterOperadoresGrupo(Grupo grupo) throws SQLException;
}
