package com.dezrill.serviceslesson;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class TextHandleViewModel extends Activity {
    private Data dataModel;

    final String LOG_TAG = "myLogs";

    private final int TASK1_CODE=1;
    private final int TASK2_CODE=2;
    private final int TASK3_CODE=3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public static final String PARAM_TIME="time";
    public static final String PARAM_PINTENT="pendingIntent";
    public static final String PARAM_RESULT="result";

    public TextHandleViewModel() {
        dataModel=new Data();
    }

    public void StartService() {
        PendingIntent pi;
        Intent intent;

        pi=createPendingResult(TASK1_CODE,null,0);
        intent=new Intent(this, DataService.class).putExtra(PARAM_TIME,7).putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi=createPendingResult(TASK2_CODE,null,0);
        intent=new Intent(this, DataService.class).putExtra(PARAM_TIME,4).putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi=createPendingResult(TASK3_CODE,null,0);
        intent=new Intent(this, DataService.class).putExtra(PARAM_TIME,6).putExtra(PARAM_PINTENT, pi);
        startService(intent);
    }

    public Data getDataModel() {
        return dataModel;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "requestCode="+requestCode+ ", resultCode="+resultCode);

        if (requestCode==STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE: dataModel.setInTxt1("Task1 start"); break;
                case TASK2_CODE: dataModel.setInTxt2("Task2 start"); break;
                case TASK3_CODE: dataModel.setInTxt3("Task3 start"); break;
            }
        }

        if (requestCode==STATUS_FINISH) {
            int result=data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE: dataModel.setInTxt1("Task1 finish=" + result); break;
                case TASK2_CODE: dataModel.setInTxt2("Task2 finish=" + result); break;
                case TASK3_CODE: dataModel.setInTxt3("Task3 finish=" + result); break;
            }
        }
    }
}
