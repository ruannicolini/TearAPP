/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Grupo;
import domain.Operacao;


public interface OperacaoDao {
    public Vector<Operacao> obterOperacoes() throws SQLException;
    public Operacao obterOperacao(final long id) throws SQLException;
}
