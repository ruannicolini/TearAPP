/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.*;

/**
 *
 * @author Giovany
 */
public class Conexao {
    private Connection databaseConnection = null;


    public void setConnection(Connection connection) {
        this.databaseConnection = connection;
    }

    public void setAutoCommit(boolean b) throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.setAutoCommit(b);
    }
    public void commit() throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.commit();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (this.databaseConnection != null)
            return databaseConnection.prepareStatement(sql);
        else
            return null;
    }

    public void rollback() throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.rollback();
    }

    public void close() throws SQLException {
        if (this.databaseConnection != null)
            this.databaseConnection.close();
    }
}
