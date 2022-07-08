package com.zli.megaquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zli.megaquiz.R;

public class EndActivity extends AppCompatActivity {

    private Button backToStartButton;
    private TextView time;
    private TextView score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        backToStartButton = findViewById(R.id.backToStartButton);
        time = findViewById(R.id.usedTimeTxt);
        score = findViewById(R.id.scoreTxt);

        backToStartButton.setOnClickListener(view -> {
            Intent resultIntent = new Intent(this, MainActivity.class);
            startActivity(resultIntent);
        });
    }
}