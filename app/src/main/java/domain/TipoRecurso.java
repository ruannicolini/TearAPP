package domain;

/**
 * Created by Ruan on 29/11/2015.
 */
public class TipoRecurso {
    private int idTipoRecurso;
    private String Descricao;

    public TipoRecurso() {
    }

    public int getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(int idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
