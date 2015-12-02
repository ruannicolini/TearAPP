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
    private ArrayList<Batida> batidas;
    private ArrayList<TipoRecurso> recursos;

    public Cronometragem() {
    }

    public Cronometragem(Cronometrista cronometrista, Grupo grupo, Operador operador, Produto produto, Tecido tecido, Operacao operacao, ArrayList<Batida> batidas, ArrayList<TipoRecurso> recursos) {
        this.cronometrista = cronometrista;
        this.grupo = grupo;
        this.operador = operador;
        this.produto = produto;
        this.tecido = tecido;
        this.operacao = operacao;
        this.batidas = batidas;
        this.recursos = recursos;
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

    public void setBatidas(ArrayList<Batida> batidas) {
        this.batidas = batidas;
    }

    public ArrayList<TipoRecurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(ArrayList<TipoRecurso> recursos) {
        this.recursos = recursos;
    }
}
