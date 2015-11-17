/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import domain.Cronometrista;
import java.util.Vector;


public interface CronometristaDao {
    public Vector<Cronometrista> obterCronometristas() throws SQLException;
    public Cronometrista obterCronometrista(final long id) throws SQLException;
}
