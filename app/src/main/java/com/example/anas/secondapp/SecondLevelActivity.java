package com.example.anas.secondapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class SecondLevelActivity extends AppCompatActivity {
    static int points =0;
    boolean startedGame = false ;
    boolean gameOver = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int temp = points ;
        points = 0 ;
        Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
        intent.putExtra("score", temp);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level);
        final Random r = new Random();
        final ImageButton blue = findViewById(R.id.blue);
        final TextView score = findViewById(R.id.score);
        final TextView time = findViewById(R.id.timer);
        final ImageButton red = findViewById(R.id.red);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int width = displaymetrics.widthPixels;
        final int height = displaymetrics.heightPixels;

        final CountDownTimer timerBlue = new CountDownTimer(700, 700) {
            @Override
            public void onTick(long millisUntilFinished) {


            }
            //set random position when timer finishes
            @Override
            public void onFinish() {
                if(startedGame){
                blue.setX(r.nextInt(950));
                blue.setY(r.nextInt(1500) + 80);

                }
                //timer starts again when finished
                this.start();
            }
        };

        final CountDownTimer timerRed = new CountDownTimer(700, 700) {
            @Override
            public void onTick(long millisUntilFinished) {


            }
            //set random position when timer finishes
            @Override
            public void onFinish() {
                if(startedGame){
                    red.setX(r.nextInt(950));
                    red.setY(r.nextInt(1500) + 80);
                }
                //timer starts again when finished
                this.start();
            }
        };






        final CountDownTimer timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //update state of timer every second
                time.setText("" + millisUntilFinished / 1000);
            }
        /*
        onFinisch set to display the dialog message

        */
            @Override
            public void onFinish() {
                time.setText("0");
                gameOver = true;
                startedGame = false;
                final Dialog dialog = new Dialog(SecondLevelActivity.this);
                dialog.setContentView(R.layout.custom);
                TextView scoreText = dialog.findViewById(R.id.score);
                Button home = dialog.findViewById(R.id.home);
                Button restart = dialog.findViewById(R.id.restart);
                TextView point = dialog.findViewById(R.id.points);
                scoreText.setText("Score : " + points);
                // avr average points/seconds
                double avr = (double) points / 30.0;
                point.setText("Points/Seconds : " + String.format("%.2f", avr));

                restart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startedGame = false;
                        dialog.dismiss();
                        time.setText("30");
                        points = 0;
                        score.setText("Score : 0");
                    }
                });

                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent registered = new Intent(getApplicationContext(), HomeActivity.class);
                        registered.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(registered);

                    }
                });
                dialog.show();


            }
        };

        /*
        implement listener for the blue dot and can trigger the timer
         */
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startedGame == false) {
                    timer.start();
                    startedGame = true;
                    timerRed.start();
                }
                blue.setX(r.nextInt(950));
                blue.setY(r.nextInt(1500) + 80);
                points++;
                score.setText("Score : " + points);
               timerBlue.start();

            }
        });

        /*
        implement listener for the red dot and can be clicked only when the game started
         */
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startedGame) {
                    red.setX(r.nextInt(950));
                    red.setY(r.nextInt(1500) + 80);
                    score.setText("Score : " + points);
                    points--;


                }
            }
        });

    }
}
