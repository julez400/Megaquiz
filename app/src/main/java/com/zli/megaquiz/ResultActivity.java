package com.zli.megaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button resumeButton;
    private TextView result;
    private TextView correctAnwser;

    private boolean results;
    private String correctAnwsers;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resumeButton = findViewById(R.id.rsumeButton);
        result = findViewById(R.id.resultTxt);
        correctAnwser = findViewById(R.id.anwserTxt);

        Intent caller = getIntent();
        results = caller.getBooleanExtra("result",false);
        correctAnwsers = caller.getStringExtra("correctAnwser");
        counter = caller.getIntExtra("counter", 0);



        if (results == true){
            result.setText("Correct!");
        }
        else {
            result.setText("False");
        }

        correctAnwser.setText(correctAnwsers);

        if (counter <= 10){
            resumeButton.setOnClickListener(view -> {
                Intent backIntent = new Intent(this, QuestionActivity.class);
                backIntent.putExtra("counter", counter);
                startActivity(backIntent);
            });
        }
        else {
            resumeButton.setOnClickListener(view -> {
                Intent endIntent = new Intent(this, EndActivity.class);
                startActivity(endIntent);
            });
        }



    }
}