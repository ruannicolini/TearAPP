package domain;

/**
 * Created by Ruan on 27/11/2015.
 */
public class Operador {
    private int cod;
    private String nome;
    private Grupo grupo;

    public Operador() {
    }

    public Operador(int cod, String nome, Grupo grupo) {
        this.cod = cod;
        this.nome = nome;
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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
