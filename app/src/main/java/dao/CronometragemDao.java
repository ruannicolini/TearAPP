/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometragem;
import domain.Grupo;


public interface CronometragemDao {
    public void inserirCronometragem(Cronometragem cronometragem) throws  SQLException;
    public int buscaIdParametro(String tipo) throws  SQLException;
}
