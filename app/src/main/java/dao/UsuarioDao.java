/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometrista;
import util.NullConnectionException;


public interface UsuarioDao {
    public Boolean Login(final String username, final String senha) throws SQLException, NullConnectionException;
}
