package domain;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Cronometragem {
    private Cronometrista cronometrista;
    private Grupo grupo;
    private Operador operador;
    private Produto produto;
    private Tecido tecido;
    private Operacao operacao;

    public Cronometragem() {
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Cronometrista getCronometrista() {
        return cronometrista;
    }

    public void setCronometrista(Cronometrista cronometrista) {
        this.cronometrista = cronometrista;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Tecido getTecido() {
        return tecido;
    }

    public void setTecido(Tecido tecido) {
        this.tecido = tecido;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }
}
