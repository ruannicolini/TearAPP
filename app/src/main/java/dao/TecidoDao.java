/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.Tecido;
import util.NullConnectionException;


public interface TecidoDao {
    public Vector<Tecido> obterTecidos() throws SQLException, NullConnectionException;
    public Tecido obterTecido(final long id) throws SQLException;
}
