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

/**
 * @author Julian Wittmann
 */
public class QuestionActivity extends AppCompatActivity {

    private QuestionService questionService;
    private boolean isQuestionServiceBound = false;

    //UI References
    private Button aButton;
    private Button bButton;
    private Button cButton;
    private Button dButton;
    private TextView question;


    private int counter = 0;
    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Create UI References
        aButton = findViewById(R.id.aButton);
        bButton = findViewById(R.id.bButton);
        cButton = findViewById(R.id.cButton);
        dButton = findViewById(R.id.dButton);
        question = findViewById(R.id.questionTxt);
    }

    /**
     * Update the question UI
     */
    public void updateQuestionUI() {
        runOnUiThread(() -> {
            question.setText(String.valueOf(questionService.getQuestions(counter)));
            aButton.setText(String.valueOf(questionService.getAnwsers(counter,0)));
            bButton.setText(String.valueOf(questionService.getAnwsers(counter,1)));
            cButton.setText(String.valueOf(questionService.getAnwsers(counter,2)));
            dButton.setText(String.valueOf(questionService.getAnwsers(counter,3)));
        });
    }

    public void anwserQuestion() {
        runOnUiThread(() -> {
            aButton.setOnClickListener(view -> {
                    if (aButton.getText() == questionService.getCorrectAnwsers(counter)){
                        result = true;
                        Intent resultIntent = new Intent(this, ResultActivity.class);
                        resultIntent.putExtra("result", result);
                        resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                        resultIntent.putExtra("counter", counter);
                        startActivity(resultIntent);
                    }
                    else{
                        result = false;
                        Intent resultIntent = new Intent(this, ResultActivity.class);
                        resultIntent.putExtra("result", result);
                        resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                        resultIntent.putExtra("counter", counter);
                        startActivity(resultIntent);
                    }
            });
            bButton.setOnClickListener(view -> {
                if (bButton.getText() == questionService.getCorrectAnwsers(counter)){
                    result = true;
                    Intent resultIntent = new Intent(this, ResultActivity.class);
                    resultIntent.putExtra("result", result);
                    resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                    resultIntent.putExtra("counter", counter);
                    startActivity(resultIntent);
                }
                else{
                    result = false;
                    Intent resultIntent = new Intent(this, ResultActivity.class);
                    resultIntent.putExtra("result", result);
                    resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                    resultIntent.putExtra("counter", counter);
                    startActivity(resultIntent);
                }
            });
            cButton.setOnClickListener(view -> {
                if (cButton.getText() == questionService.getCorrectAnwsers(counter)){
                    result = true;
                    Intent resultIntent = new Intent(this, ResultActivity.class);
                    resultIntent.putExtra("result", result);
                    resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                    resultIntent.putExtra("counter", counter);
                    startActivity(resultIntent);
                }
                else{
                    result = false;
                    Intent resultIntent = new Intent(this, ResultActivity.class);
                    resultIntent.putExtra("result", result);
                    resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                    resultIntent.putExtra("counter", counter);
                    startActivity(resultIntent);
                }
            });
            dButton.setOnClickListener(view -> {
                if (dButton.getText() == questionService.getCorrectAnwsers(counter)){
                    result = true;
                    Intent resultIntent = new Intent(this, ResultActivity.class);
                    resultIntent.putExtra("result", result);
                    resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                    resultIntent.putExtra("counter", counter);
                    startActivity(resultIntent);
                }
                else{
                    result = false;
                    Intent resultIntent = new Intent(this, ResultActivity.class);
                    resultIntent.putExtra("result", result);
                    resultIntent.putExtra("correctAnwser", questionService.getCorrectAnwsers(counter));
                    resultIntent.putExtra("counter", counter);
                    startActivity(resultIntent);
                }
            });
        });
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
                  updateQuestionUI();
                  anwserQuestion();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isQuestionServiceBound = false;
        }
    };

}