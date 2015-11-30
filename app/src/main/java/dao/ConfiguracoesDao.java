/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import android.content.Context;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import domain.Grupo;


public interface ConfiguracoesDao {
    public ArrayList<String> LerConfiguracoes(Context context) throws SQLException, IOException ;
    public void gravarConfiguracoes(Context context, String ip, String NomeBanco, String usuarioBanco, String senhaUsuario, String porta) throws SQLException, IOException;
}
