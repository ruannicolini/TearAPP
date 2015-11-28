/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;
import java.util.Vector;

import domain.Cronometrista;
import domain.Produto;


public interface ProdutoDao {
    public Vector<Produto> obterProdutos() throws SQLException;
    public Produto obterProduto(final long id) throws SQLException;
}
