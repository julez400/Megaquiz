package com.zli.megaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private QuestionService questionService;
    private boolean isQuestionServiceBound = false;

    private Button aButton;
    private Button bButton;
    private Button cButton;
    private Button dButton;
    private TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        aButton = findViewById(R.id.aButton);
        bButton = findViewById(R.id.bButton);
        cButton = findViewById(R.id.cButton);
        dButton = findViewById(R.id.dButton);
        question = findViewById(R.id.questionTxt);


/*        question.setText(String.valueOf(questionService.getQuestion()));
        aButton.setText(String.valueOf(questionService.getAnwsers(0)));
        bButton.setText(String.valueOf(questionService.getAnwsers(1)));
        cButton.setText(String.valueOf(questionService.getAnwsers(2)));
        dButton.setText(String.valueOf(questionService.getAnwsers(3)));*/



    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent bindQuestionServiceIntent = new Intent(this, QuestionService.class);
        bindService(bindQuestionServiceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        isQuestionServiceBound = false;
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            QuestionService.QuestionBinder binder = (QuestionService.QuestionBinder)  iBinder;
            questionService = binder.getService();
            isQuestionServiceBound = true;
            questionService.getQuestionFromAPI(new QuestionServiceEventListener() {
                @Override
                public void onDone() {
                    System.out.println(questionService.getQuestion());
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isQuestionServiceBound = false;
        }
    };

}