package domain;

/**
 * Created by Ruan on 28/11/2015.
 */
public class Batida {
    private int idBatida;
    private Cronometragem cronometragem;
    private int minutos;
    private int segundos;
    private int centezimos;
    private boolean utilizar;

    public Batida() {
    }

    public boolean isUtilizar() {
        return utilizar;
    }

    public void setUtilizar(boolean utilizar) {
        this.utilizar = utilizar;
    }

    public Cronometragem getCronometragem() {
        return cronometragem;
    }

    public void setCronometragem(Cronometragem cronometragem) {
        this.cronometragem = cronometragem;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getCentezimos() {
        return centezimos;
    }

    public void setCentezimos(int centezimos) {
        this.centezimos = centezimos;
    }

    public int getIdBatida() {
        return idBatida;
    }

    public void setIdBatida(int idBatida) {
        this.idBatida = idBatida;
    }
}

