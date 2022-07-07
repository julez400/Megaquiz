package com.zli.megaquiz;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuestionService extends Service {

    private String question = "question";
    private String correctAnswer = "correct_answer";
    private String incorrectAnswer = "incorrect_answer";
    private String[] questions = new String[9];
    private String[] correctAnswers = new String[9];


    private final IBinder binder = new QuestionBinder();

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

    public void getQuestion() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {
            try {
                URL url = new URL("https://opentdb.com/api.php?amount=10&type=multiple");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                reader.beginObject();
                reader.nextName();
                reader.beginArray();
                reader.beginObject();

                int i = 0;
                int j = 0;
                int k = 0;


                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals(question)) {
                        questions[i] = String.valueOf(reader.nextString());
                        i++;
                    } else if (name.equals(correctAnswer)) {
                        correctAnswers[j] = String.valueOf(reader.nextString());
                        j++;
                    /*
                } else if (name.equals(incorrectAnswer)) {
                    correctAnswers[j] = String.valueOf(reader.nextString());
                    k =;
                     */
                    } else {
                        reader.skipValue();
                    }
                }
                reader.close();
                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.post(() -> {

            });
        });
    }


}