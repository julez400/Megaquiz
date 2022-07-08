package com.zli.megaquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import com.zli.megaquiz.QuestionActivity;
import com.zli.megaquiz.R;
import com.zli.megaquiz.controller.EndActivity;

public class ResultActivity extends AppCompatActivity {

    private Button resumeButton;
    private TextView result;
    private TextView correctAnwser;

    private boolean results;
    private String correctAnwsers;
    private int counter;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resumeButton = findViewById(R.id.rsumeButton);
        result = findViewById(R.id.resultTxt);
        correctAnwser = findViewById(R.id.anwserTxt);

        Intent caller = getIntent();
        results = caller.getBooleanExtra("result", false);
        correctAnwsers = caller.getStringExtra("correctAnwser");
        counter = caller.getIntExtra("counter", 0);


        if (results == true) {
            result.setText("Correct!");
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        } else {
            result.setText("False");
        }

        correctAnwser.setText(correctAnwsers);

        resumeButton.setOnClickListener(view -> {
            if (counter <= 10) {
                counter++;
                Intent backIntent = new Intent(this, QuestionActivity.class);
                backIntent.putExtra("counter", counter);
                startActivity(backIntent);

            } else {
                Intent endIntent = new Intent(this, EndActivity.class);
                startActivity(endIntent);

            }
        });


    }
}