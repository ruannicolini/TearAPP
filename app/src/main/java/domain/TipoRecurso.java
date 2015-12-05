package domain;

import java.io.Serializable;

/**
 * Created by Ruan on 29/11/2015.
 */
public class TipoRecurso implements Serializable {
    private int idTipoRecurso;
    private String Descricao;

    public TipoRecurso() {
    }

    public TipoRecurso(int idTipoRecurso, String descricao) {
        this.idTipoRecurso = idTipoRecurso;
        Descricao = descricao;
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

    @Override
    public String toString() {
        return this.getDescricao();
    }

    @Override public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        TipoRecurso rec = (TipoRecurso)o;
        if (this.idTipoRecurso != rec.getIdTipoRecurso()) {
            return false;
        }
        return true;
    }
}
