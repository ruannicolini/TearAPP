package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Produto implements Serializable {
    private int idProduto;
    private String descricao;

    public Produto() {
    }

    public Produto(int idProduto, String descricao) {
        this.idProduto = idProduto;
        this.descricao = descricao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
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
