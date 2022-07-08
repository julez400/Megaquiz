package com.zli.megaquiz;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.zli.megaquiz.model.Question;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionService extends Service {

    private final IBinder binder = new QuestionBinder();

    private ArrayList<Question> questions = new ArrayList<>();

    public class QuestionBinder extends Binder {
        public QuestionService getService() {
            return QuestionService.this;
        }
    }

    public QuestionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void getQuestionFromAPI(QuestionServiceEventListener questionServiceEventListener) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            try {
                URL url = new URL("https://opentdb.com/api.php?amount=10&type=multiple");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[8192];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                String jsonResult = result.toString("UTF-8");
                JSONObject json = new JSONObject(jsonResult);


                for (int i=0; i <= 9; i++){

                    JSONObject quiz = (JSONObject) json.getJSONArray("results").get(i);
                    Question question = new Question();
                    question.setQuestion(quiz.getString("question"));
                    question.setCorrectAnwser(quiz.getString("correct_answer"));
                    question.setWrongAnwser1((String) quiz.getJSONArray("incorrect_answers").get(0));
                    question.setWrongAnwser2((String) quiz.getJSONArray("incorrect_answers").get(1));
                    question.setWrongAnwser3((String) quiz.getJSONArray("incorrect_answers").get(2));
                    questions.add(question);

                }

                inputStream.close();

                questionServiceEventListener.onDone();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public String getQuestions(int i){
        return questions.get(i).getQuestion();
    }

    public String getAnwsers(int i, int anwser){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(questions.get(i).getCorrectAnwser());
        answers.add(questions.get(i).getWrongAnwser1());
        answers.add(questions.get(i).getWrongAnwser2());
        answers.add(questions.get(i).getWrongAnwser3());

        return answers.get(anwser);
    }
    public String getCorrectAnwsers(int i){
        return questions.get(i).getCorrectAnwser();
    }
}

interface QuestionServiceEventListener {
    void onDone();
}