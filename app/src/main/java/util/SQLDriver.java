/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author Giovany
 */
public abstract class SQLDriver {
    protected String nomeBanco;
    protected String usuario;
    protected String senha;
    
    public SQLDriver(String nomeBanco, String usuario, String senha){
        this.nomeBanco = nomeBanco;
        this.usuario = usuario;
        this.senha = senha;
    }    
}
