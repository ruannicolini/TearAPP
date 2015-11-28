package domain;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Grupo {
    private int idGrupo;
    private String descricao;

    public Grupo() {
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