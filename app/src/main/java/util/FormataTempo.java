package util;

/**
 * Created by Ruan on 28/11/2015.
 */
public class FormataTempo {

    public static String mostrarTempo(int tempoCentesimo){
        int centesimos,segundos,minutos;
        String formatedSeconds,formatedMinutes;

        centesimos = tempoCentesimo%10;
        segundos=(tempoCentesimo/=10)%60;
        minutos=(tempoCentesimo/=60)%60;

        //format output
        formatedSeconds=Integer.toString(segundos/10)+
                Integer.toString(segundos%10);
        formatedMinutes=Integer.toString(minutos/10)+
                Integer.toString(minutos%10);

        String timeString =
                formatedMinutes+":"+
                        formatedSeconds+"."+
                        Integer.toString(centesimos);
        return timeString;
    }
}
