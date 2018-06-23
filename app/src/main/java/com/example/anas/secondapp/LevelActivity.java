package com.example.anas.secondapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Button level1 = findViewById(R.id.level1);
        Button level2 = findViewById(R.id.level2);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLevel = new Intent(LevelActivity.this , GameActivity.class);
                LevelActivity.this.startActivity(startLevel);

            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLevel2 = new Intent(LevelActivity.this , SecondLevelActivity.class);
                LevelActivity.this.startActivity(startLevel2);
            }
        });
    }
}
