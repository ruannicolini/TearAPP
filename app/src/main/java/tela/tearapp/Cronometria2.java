package tela.tearapp;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import domain.Batida;
import domain.Cronometragem;
import util.FormataTempo;

public class Cronometria2 extends Activity {
    Cronometragem cronometragem = new Cronometragem();
    List<Batida> batidas = new ArrayList();

    //Componentes
    private Button btnInicio;
    private Button btnLap;
    private TextView textViewCronometro;

    //Tempo
    private Timer timer;
    private LayoutTransition transition;
    private LinearLayout linearLayout;

    //Aux
    private int tempoCorrenteVisor = 0; // Tempo que aparece no TextViewCronometro
    private int tempoLap = 0; // Tempo da Batida
    private int contaLap = 0; // Conta quantas batidas foram feitas
    private boolean Status_lap; // Verifica se ja teve Alguma Batida
    private boolean btnIniciarPressionado = false; //Verifica o Status do botão para Alterar o comportamento. BtnIniciar == btnParar   ----   BtnLap ==BtnReset


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria2);

        // Recebe Parametros da Activity Cronometria
        Bundle params = getIntent().getExtras();
        if(params != null){
            cronometragem = (Cronometragem) params.getSerializable("cronometragem");
            System.out.println("Chegou na Cronometria2");
        }

        btnInicio = (Button) findViewById(R.id.btn_Iniciar);
        btnLap = (Button) findViewById(R.id.btn_lap);
        textViewCronometro = (TextView) findViewById(R.id.textViewCronometro);
        textViewCronometro.setTextSize(55);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cronometria2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetCronometro() {
        timer.cancel();
        tempoCorrenteVisor = 0;
        tempoLap = 0;
        contaLap = 0;
        textViewCronometro.setText(FormataTempo.mostrarTempo(tempoCorrenteVisor, null));
        btnLap.setEnabled(false);
        btnLap.setText(R.string.btn_lap);
        btnLap.setBackgroundResource(R.drawable.btn_reset_states);

        if (Status_lap) {
            linearLayout.setLayoutTransition(null);
            linearLayout.removeAllViews();
            batidas.clear();
            Status_lap = false;
        }
    }

    public void lapCronometro(View view) {
        if(btnIniciarPressionado == false){
            resetCronometro();
        }else {
            Status_lap = true;
            contaLap++;

            transition = new LayoutTransition();
            transition.setAnimator(LayoutTransition.CHANGE_APPEARING,null);
            transition.setStartDelay(LayoutTransition.APPEARING,0);

            linearLayout = (LinearLayout) findViewById(R.id.layout);
            linearLayout.setLayoutTransition(transition);

            TextView lapDisplay = new TextView(this);
            ImageView imageView = new ImageView(this);
            imageView.setFocusableInTouchMode(true);

            lapDisplay.setGravity(Gravity.CENTER);
            lapDisplay.setTextColor(Color.WHITE);
            lapDisplay.setTextSize(20);

            linearLayout.addView(lapDisplay);
            linearLayout.addView(imageView);

            imageView.requestFocus();

            Batida batida = new Batida();
            batida.setUtilizar(true);

            lapDisplay.setText(String.format("Lap %d: %s", contaLap, FormataTempo.mostrarTempo(tempoLap, batida)));
            imageView.setImageResource(R.drawable.divider);
            tempoLap = 0;
            batidas.add(batida);
        }
    }

    public void pararCronometro() {
        btnInicio.setBackgroundResource(R.drawable.btn_start_states);
        btnInicio.setText(R.string.btn_start);
        btnLap.setEnabled(true);
        btnLap.setBackgroundResource(R.drawable.btn_lap_states);
        btnLap.setText(R.string.btn_reset);

        btnIniciarPressionado = false;
        timer.cancel();
    }

    public void iniciarCronometro(View view) {
        if (btnIniciarPressionado == true) {
            pararCronometro();
        } else {
            btnIniciarPressionado = true;

            btnInicio.setBackgroundResource(R.drawable.btn_stop_states);
            btnInicio.setText(R.string.btn_stop);

            btnLap.setBackgroundResource(R.drawable.btn_lap_states);
            btnLap.setText(R.string.btn_lap);
            btnLap.setEnabled(true);

            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            tempoCorrenteVisor += 1;
                            tempoLap += 1;

                            // Alteração textViewCronometro
                            textViewCronometro.setText(FormataTempo.mostrarTempo(tempoCorrenteVisor, null));
                        }
                    });
                }
            }, 0, 100);
        }
    }

    public void chamaCronometria3(View view){
        //Passa Os dados obtidos pra Activity/tela Cronometria3
        Intent intent = new Intent(getApplicationContext(), Cronometria3.class);
        startActivity(intent);
    }

}
