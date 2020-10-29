package com.example.mvvmudemy01.view.part07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.workmanager.Part07DemoWorker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Part07WorkManagerActivity extends AppCompatActivity {

    public static final String KEY_COUNT_VALUE = "key_count";
    private FloatingActionButton fab;
    private TextView txtstaus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part07_work_manager);
        fab= findViewById(R.id.fab);
        txtstaus= findViewById(R.id.txtstaus);

        Data data = new Data.Builder()
                .putInt(KEY_COUNT_VALUE,1750)
                .build();
        Constraints constraints=new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        final OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(Part07DemoWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });

        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {/**workInfo.getState()
                     */
                        txtstaus.setText(workInfo.getState().name());
                        if (workInfo.getState().isFinished()){
                            Data data1=workInfo.getOutputData();
                            String message=data1.getString(Part07DemoWorker.KEY_WORKER);
                            Toast.makeText(Part07WorkManagerActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }
}