package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.AdapterMain;
import com.example.mvvmudemy01.model.infoMain;
import com.example.mvvmudemy01.viewmodel.Part01ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Part01ViewModelActivity extends AppCompatActivity {

    private int clickCountnot = 0;

    Part01ViewModel part01ViewModel;

    Button btnCountNotViewmodel,btnCountViewmodel;
    TextView txtCountNotViewmodel,txtCountViewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part01_view_model);
        init();

        notView();
        Vieww();



    }
    private void Vieww() {
        LiveData<Integer> count = part01ViewModel.getInitialCount();
        count.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txtCountViewmodel.setText("Count is: "+integer);

            }
        });
        btnCountViewmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txtCountViewmodel.setText("Count is: "+part01ViewModel.getCurrentCount());

                part01ViewModel.getCurrentCount();



            }

        });
    }

    private void notView() {

        txtCountNotViewmodel.setText("Count is: "+clickCountnot);
        btnCountNotViewmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCountNotViewmodel.setText("Count is: "+clickCountnot++);
            }
        });
    }

    private void init() {
        btnCountNotViewmodel = findViewById(R.id.btnCountNotViewmodel);
        btnCountViewmodel = findViewById(R.id.btnCountViewmodel);
        txtCountViewmodel = findViewById(R.id.txtCountViewmodel);
        txtCountNotViewmodel = findViewById(R.id.txtCountNotViewmodel);

        part01ViewModel = new ViewModelProvider(this).get(Part01ViewModel.class);




    }
}