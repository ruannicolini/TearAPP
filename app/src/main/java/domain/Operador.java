package domain;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Operador {
    private int idOperador;
    private String nome;
    private Grupo grupo;

    public Operador() {
    }

    public Operador(int idOperador, String nome, Grupo grupo) {
        this.idOperador = idOperador;
        this.nome = nome;
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
