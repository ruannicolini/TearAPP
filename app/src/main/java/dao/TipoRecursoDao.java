/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.TipoRecurso;


public interface TipoRecursoDao {
    public Vector<TipoRecurso> obterTiposRecurso() throws SQLException;
    public TipoRecurso obterTipoRecurso(final long id) throws SQLException;
}
