package com.example.anas.secondapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_game);
        final Random r= new Random();
        final ImageButton blue = findViewById(R.id.blue);
        final TextView score = findViewById(R.id.score);
        final TextView time = findViewById(R.id.timer);


        //get displayMetrics
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        final int width = displaymetrics.widthPixels;
        final int height = displaymetrics.heightPixels;


       final CountDownTimer timer = new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(""+millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                time.setText("0");
                gameOver = true ;
                final Dialog dialog = new Dialog(GameActivity.this);
                dialog.setContentView(R.layout.custom);
                TextView scoreText = dialog.findViewById(R.id.score);
                Button home = dialog.findViewById(R.id.home);
                Button restart  = dialog.findViewById(R.id.restart);
                TextView point = dialog.findViewById(R.id.points);
                scoreText.setText("Score : "+points);
                double avr = (double)points / 30.0 ;
                point.setText("Points/Seconds : "+String.format("%.2f", avr));

                restart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startedGame=false;
                        dialog.dismiss();
                        time.setText("30");
                        points=0;
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

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int xx=r.nextInt(1000 );
               // int yy=r.nextInt(1500 );
              if(startedGame == false) {
                   timer.start();
                   startedGame = true;
               }
                startedGame = true ;
                blue.setX(r.nextInt(950)) ;
                blue.setY(r.nextInt(1500)+80);
                points++ ;
                score.setText("Score : "+points);

            }
        });




    }
}
