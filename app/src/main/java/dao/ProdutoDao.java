/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometrista;
import domain.Produto;
import util.NullConnectionException;


public interface ProdutoDao {
    public Vector<Produto> obterProdutos() throws SQLException, NullConnectionException;
    public Produto obterProduto(final long id) throws SQLException;
}
