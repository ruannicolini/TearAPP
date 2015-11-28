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

    private Button btnInicio;
    private Button btnLap;
    private TextView textViewCronometro;
    private Timer timer;
    private LayoutTransition transition;
    private LinearLayout linearLayout;

    //Tempo
    private int currentTime = 0;
    private int lapTime = 0;
    private int lapCounter=0;
    private boolean lapViewExists;
    private boolean isButtonStartPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometria2);

        // Recebe Parametros da Activity Cronometria
        Bundle params = getIntent().getBundleExtra("args_tela1");
        if(params != null){
            //cronometragem = (Cronometragem) params.getSerializable("cronometragem");
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
        currentTime = 0;
        lapTime = 0;
        lapCounter=0;
        textViewCronometro.setText(FormataTempo.mostrarTempo(currentTime));
        btnLap.setEnabled(false);
        btnLap.setText(R.string.btn_lap);
        btnLap.setBackgroundResource(R.drawable.btn_reset_states);

        if (lapViewExists) {
            linearLayout.setLayoutTransition(null);
            linearLayout.removeAllViews();
            lapViewExists = false;
        }
    }

    public void lapCronometro(View view) {
        if(!isButtonStartPressed){
            resetCronometro();
        }else {
            lapViewExists = true;
            lapCounter++;

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
            lapDisplay.setTextSize(30);

            linearLayout.addView(lapDisplay);
            linearLayout.addView(imageView);

            imageView.requestFocus();

            lapDisplay.setText(String.format("Lap %d: %s", lapCounter, FormataTempo.mostrarTempo(lapTime)));
            imageView.setImageResource(R.drawable.divider);
            lapTime = 0;
        }
    }

    public void pararCronometro() {
        btnInicio.setBackgroundResource(R.drawable.btn_start_states);
        btnInicio.setText(R.string.btn_start);
        btnLap.setEnabled(true);
        btnLap.setBackgroundResource(R.drawable.btn_lap_states);
        btnLap.setText(R.string.btn_reset);

        isButtonStartPressed = false;
        timer.cancel();
    }

    public void iniciarCronometro(View view) {
        if (isButtonStartPressed) {
            pararCronometro();
        } else {
            isButtonStartPressed = true;

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
                            currentTime += 1;
                            lapTime += 1;

                            // Alteração textViewCronometro
                            textViewCronometro.setText(FormataTempo.mostrarTempo(currentTime));
                        }
                    });
                }
            }, 0, 100);
        }
    }

}
