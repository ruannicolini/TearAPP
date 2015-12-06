/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.TipoRecurso;
import util.NullConnectionException;


public interface TipoRecursoDao {
    public Vector<TipoRecurso> obterTiposRecurso() throws SQLException, NullConnectionException;
    public TipoRecurso obterTipoRecurso(final long id) throws SQLException;
}
