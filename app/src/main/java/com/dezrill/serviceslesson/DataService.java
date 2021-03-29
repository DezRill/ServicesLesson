package com.dezrill.serviceslesson;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataService extends Service {
    final String LOG_TAG = "myLogs";
    ExecutorService es;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"DataService onCreate");
        es= Executors.newFixedThreadPool(2);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "DataService onStartCommand");

        int time =intent.getIntExtra(TextHandleViewModel.PARAM_TIME, 1);
        PendingIntent pi=intent.getParcelableExtra(TextHandleViewModel.PARAM_PINTENT);

        RunProc run=new RunProc(time, startId, pi);
        es.execute(run);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class RunProc implements Runnable {
        int time;
        int startId;
        PendingIntent pi;

        public RunProc(int time, int startId, PendingIntent pi) {
            this.time=time;
            this.startId=startId;
            this.pi=pi;
            Log.d(LOG_TAG, "RunProc#"+startId+" start, time="+time);
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "RunProc#"+startId+" start, time="+time);
            try {
                pi.send(TextHandleViewModel.STATUS_START);
                TimeUnit.SECONDS.sleep(time);

                Intent intent=new Intent().putExtra(TextHandleViewModel.PARAM_RESULT, time*100);
                pi.send(DataService.this, TextHandleViewModel.STATUS_FINISH, intent);
            }
            catch (PendingIntent.CanceledException | InterruptedException e) {
                e.printStackTrace();
            }
            Stop();
        }

        private void Stop() {
            Log.d(LOG_TAG, "RunProc#"+startId+ " end, stopSelfResult("+startId+")="+stopSelfResult(startId));
        }
    }
}
