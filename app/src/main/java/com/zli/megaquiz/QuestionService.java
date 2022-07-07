package com.zli.megaquiz;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class QuestionService extends Service {

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


}