package com.example.mvvmudemy01.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mvvmudemy01.view.part07.Part07WorkManagerActivity;

public class Part07DemoWorker extends Worker {

    public static final String KEY_WORKER = "key_value";

    public Part07DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        int countLimit = data.getInt(Part07WorkManagerActivity.KEY_COUNT_VALUE, 0);
        for (int i = 0; i < countLimit; i++) {
            Log.i("TAG", "Part07DemoWorker:data Count is" + i);
        }

        for (int i = 0; i < 10000; i++) {
            Log.i("TAG", "Part07DemoWorker: Count is" + i);
        }

        Data dataToSend = new Data.Builder()
                .putString(KEY_WORKER,"Task done Successfully!")
                .build();


        return Result.success(dataToSend);
        //return Result.success();
    }
}
