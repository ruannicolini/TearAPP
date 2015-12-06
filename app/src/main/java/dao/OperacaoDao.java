/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.Operacao;
import util.NullConnectionException;


public interface OperacaoDao {
    public Vector<Operacao> obterOperacoes() throws SQLException, NullConnectionException;
    public Operacao obterOperacao(final long id) throws SQLException;
}
