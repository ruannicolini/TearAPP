package domain;

import java.io.Serializable;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Grupo implements Serializable {
    private int idGrupo;
    private String descricao;

    public Grupo() {
    }

    public Grupo(int idGrupo, String descricao) {
        this.idGrupo = idGrupo;
        this.descricao = descricao;
    }

    public Grupo(String descricao, int idGrupo) {
        this.descricao = descricao;
        this.idGrupo = idGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setCod(int idGrupo) {
        this.idGrupo = idGrupo;
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
