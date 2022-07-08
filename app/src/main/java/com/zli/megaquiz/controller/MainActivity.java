package com.zli.megaquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zli.megaquiz.QuestionActivity;
import com.zli.megaquiz.R;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(view -> {
            Intent questionIntent = new Intent(this, QuestionActivity.class);
            startActivity(questionIntent);
        });
    }
}