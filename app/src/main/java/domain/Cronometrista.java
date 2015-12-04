package domain;

import java.io.Serializable;

/**
 * Created by Ruan on 05/11/2015.
 */
public class Cronometrista implements Serializable{
    private String nome;
    private int idCronometrista;

    public Cronometrista(int idCronometrista, String nome) {
        this.idCronometrista = idCronometrista;
        this.nome = nome;
    }

    public int getIdCronometrista() {
        return idCronometrista;
    }

    public void setIdCronometrista(int idCronometrista) {
        this.idCronometrista = idCronometrista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cronometrista(String nome, int idCronometrista) {
        this.nome = nome;
        this.idCronometrista = idCronometrista;
    }


    @Override
    public String toString() {
        return this.getNome();
    }

    public Cronometrista() {
        super();
    }
}
