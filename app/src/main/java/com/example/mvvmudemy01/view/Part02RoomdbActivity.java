package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mvvmudemy01.R;

public class Part02RoomdbActivity extends AppCompatActivity {

    LinearLayout gotosqlite,gotoroomdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part02_roomdb);
        init();


        gotoroomdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Part02RoomdbActivity.this,Part02_1RoomdbActivity.class));

            }
        });

        gotosqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Part02RoomdbActivity.this,Part02_2RoomdbMainActivity.class));
            }
        });


    }

    private void init() {
        gotosqlite = findViewById(R.id.gotosqlite);
        gotoroomdb = findViewById(R.id.gotoroomdb);



    }
}