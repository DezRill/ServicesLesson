package com.dezrill.serviceslesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dezrill.serviceslesson.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Data dataModel;

    public static final String LOG_TAG = "myLogs";

    public static final int TASK1_CODE=1;
    public static final int TASK2_CODE=2;
    public static final int TASK3_CODE=3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public static final String PARAM_TIME="time";
    public static final String PARAM_PINTENT="pendingIntent";
    public static final String PARAM_RESULT="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModel=new Data();

        ActivityMainBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setData(dataModel);
    }

    public void onClickStart(View view) {
        StartService();
    }

    public void StartService() {
        PendingIntent pi;

        Intent intent=new Intent(this, DataService.class);
        pi=createPendingResult(TASK1_CODE, intent,0);
        intent.putExtra(PARAM_TIME,7).putExtra(PARAM_PINTENT, pi);
        startService(intent);

        intent=new Intent(this, DataService.class);
        pi=createPendingResult(TASK2_CODE, intent,0);
        intent.putExtra(PARAM_TIME,4).putExtra(PARAM_PINTENT, pi);
        startService(intent);

        intent=new Intent(this, DataService.class);
        pi=createPendingResult(TASK3_CODE, intent,0);
        intent.putExtra(PARAM_TIME,6).putExtra(PARAM_PINTENT, pi);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "requestCode="+requestCode+ ", resultCode="+resultCode);

        if (resultCode==STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE: dataModel.setInTxt1("Task1 start"); break;
                case TASK2_CODE: dataModel.setInTxt2("Task2 start"); break;
                case TASK3_CODE: dataModel.setInTxt3("Task3 start"); break;
            }
        }

        if (resultCode==STATUS_FINISH) {
            int result=data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE: dataModel.setInTxt1("Task1 finish=" + result); break;
                case TASK2_CODE: dataModel.setInTxt2("Task2 finish=" + result); break;
                case TASK3_CODE: dataModel.setInTxt3("Task3 finish=" + result); break;
            }
        }
    }
}