/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import domain.Cronometrista;
import java.util.Vector;


public interface CronometristaDao {
    public void inserirCronometrista(Cronometrista c) throws  SQLException;
    public void alterarCronometrista(Cronometrista c) throws  SQLException;
    public void excluirCronometrista(Cronometrista c) throws  SQLException;
    public Vector<Cronometrista> obterCronometristas() throws SQLException;
    public Vector<Cronometrista> obterCronometristasPorNomeOrdem(String nome, boolean crescente) throws SQLException;
}
