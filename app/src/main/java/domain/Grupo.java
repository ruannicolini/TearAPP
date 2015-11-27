package domain;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Grupo {
    private int cod;
    private String descricao;

    public Grupo() {
    }

    public Grupo(String descricao, int cod) {
        this.descricao = descricao;
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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
