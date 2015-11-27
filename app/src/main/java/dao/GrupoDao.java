/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometrista;
import domain.Grupo;


public interface GrupoDao {
    public Vector<Grupo> obterGrupos() throws SQLException;
    public Grupo obterGrupo(final long id) throws SQLException;
}
