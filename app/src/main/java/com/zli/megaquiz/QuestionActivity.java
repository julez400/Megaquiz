package com.zli.megaquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class QuestionActivity extends AppCompatActivity {

    private QuestionService questionService;
    private boolean isQuestionServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

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
            questionService.getQuestion();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isQuestionServiceBound = false;
        }
    };

}