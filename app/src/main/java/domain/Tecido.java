package domain;

import java.io.Serializable;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Tecido implements Serializable {
    private int idTecido;
    private String descricao;

    public Tecido() {
    }

    public int getIdTecido() {
        return idTecido;
    }

    public void setIdTecido(int idTecido) {
        this.idTecido = idTecido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}
