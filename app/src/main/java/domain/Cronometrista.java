package domain;

/**
 * Created by Ruan on 05/11/2015.
 */
public class Cronometrista {
    private String nome;
    private int idCronometrista;

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


    public Cronometrista() {
        super();
    }
}
