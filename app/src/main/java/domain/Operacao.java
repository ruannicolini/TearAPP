package domain;

import java.io.Serializable;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Operacao implements Serializable {
    private int idOperacao;
    private String descricao;
    private int idAcao;
    private String acao;
    private int idParte;
    private String parte;
    private int idFase;
    private String fase;

    public Operacao() { }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdAcao() {
        return idAcao;
    }

    public void setIdAcao(int idAcao) {
        this.idAcao = idAcao;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public int getIdParte() {
        return idParte;
    }

    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public int getIdFase() {
        return idFase;
    }

    public void setIdFase(int idFase) {
        this.idFase = idFase;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public int getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(int idOperacao) {
        this.idOperacao = idOperacao;
    }

    @Override
    public String toString() {

        return this.getIdOperacao() + " - " +this.getAcao() + " " + this.getParte() + " " + this.getFase();
    }
}
