package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.AdapterMain;
import com.example.mvvmudemy01.adapter.part05.AdapterPart05;
import com.example.mvvmudemy01.model.infoMain;

import java.util.ArrayList;
import java.util.List;

public class Part05Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPart05 adapterNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part05);
        init();
    }
    private void init() {
        recyclerView = findViewById(R.id.rvMain);
        adapterNav = new AdapterPart05(this, getdata());
        recyclerView.setAdapter(adapterNav);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<infoMain> getdata() {
        List<infoMain> data = new ArrayList<>();
        String title[] = {"Country-list","Country-Search","@PostAnd@Body"
                ,"Movie-List","Movie-List-MVVM"
        };


        for (int i = 0; i < title.length ; i++) {

            infoMain cur = new infoMain();
            cur.title = title[i];
            data.add(cur);

        }
        return data;

    }

}