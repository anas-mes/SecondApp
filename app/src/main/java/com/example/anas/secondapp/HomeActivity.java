package com.example.anas.secondapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button play = findViewById(R.id.btPlay);
        Button instructions = findViewById(R.id.btInstructions);
        Button score = findViewById(R.id.btScore);

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructionsIntent = new Intent(HomeActivity.this , InstructionsActivity.class);
                HomeActivity.this.startActivity(instructionsIntent);
            }
        });


        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent scoreIntent = new Intent(HomeActivity.this , ScoreActivity.class);
                HomeActivity.this.startActivity(scoreIntent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(HomeActivity.this, LevelActivity.class);
                HomeActivity.this.startActivity(playIntent);
            }
        });
    }
}
