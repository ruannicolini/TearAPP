package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Cronometragem implements Serializable {
    private Cronometrista cronometrista;
    private Grupo grupo;
    private Operador operador;
    private Produto produto;
    private Tecido tecido;
    private Operacao operacao;
    private List<Batida> batidas = new ArrayList();
    private List<TipoRecurso> recursos = new ArrayList();

    public Cronometragem() {
        this.cronometrista = new Cronometrista();
        this.grupo = new Grupo();
        this.operador = new Operador();
        this.produto = new Produto();
        this.tecido = new Tecido();
        this.operacao = new Operacao();
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

    public List<Batida> getBatidas() {
        return batidas;
    }

    public void setBatidas(List<Batida> batidas) {
        this.batidas = batidas;
    }

    public List<TipoRecurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<TipoRecurso> recursos) {
        this.recursos = recursos;
    }
}
