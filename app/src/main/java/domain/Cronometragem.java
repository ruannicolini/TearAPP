package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Cronometragem implements Serializable {
    private int idCronometragem;
    private Cronometrista cronometrista;
    private Grupo grupo;
    private Operador operador;
    private Produto produto;
    private Tecido tecido;
    private Operacao operacao;
    private ArrayList<Batida> batidas;
    private ArrayList<TipoRecurso> recursos;

    private int ritmo;
    private int tolerancia;
    private int numPecas;
    private int numOcorrencia;
    private float comprimentoProduto;
    private String referencia;
    private Date dataCronometragem;

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

    public int getRitmo() {
        return ritmo;
    }

    public void setRitmo(int ritmo) {
        this.ritmo = ritmo;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public int getNumPecas() {
        return numPecas;
    }

    public void setNumPecas(int numPecas) {
        this.numPecas = numPecas;
    }

    public int getNumOcorrencia() {
        return numOcorrencia;
    }

    public void setNumOcorrencia(int numOcorrencia) {
        this.numOcorrencia = numOcorrencia;
    }

    public float getComprimentoProduto() {
        return comprimentoProduto;
    }

    public void setComprimentoProduto(float comprimentoProduto) {
        this.comprimentoProduto = comprimentoProduto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getDataCronometragem() {
        return dataCronometragem;
    }

    public void setDataCronometragem(Date dataCronometragem) {
        this.dataCronometragem = dataCronometragem;
    }

    public int getIdCronometragem() {
        return idCronometragem;
    }

    public void setIdCronometragem(int idCronometragem) {
        this.idCronometragem = idCronometragem;
    }
}
