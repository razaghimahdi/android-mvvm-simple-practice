package com.example.mvvmudemy01.view.part05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.part05.CountryAdapter;
import com.example.mvvmudemy01.model.part05.Info;
import com.example.mvvmudemy01.model.part05.Result;
import com.example.mvvmudemy01.service.GetCountryDataService;
import com.example.mvvmudemy01.service.RetrofitInstanceGroupkt;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part05CountryListActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part05_country_list);

        pb = findViewById(R.id.pb);

        getCountries();

    }

    public Object getCountries() {

        GetCountryDataService getCountryDataService = RetrofitInstanceGroupkt.getService();
        Call<Info> call = getCountryDataService.getResults();

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info info = response.body();

                if (info !=null && info.getRestResponse() != null){
                    results=(ArrayList<Result>) info.getRestResponse().getResult();

                    for (Result r :
                            results) {
                        Log.i("TAG", "onResponse: "+"*************"+r.getName());
                    }
                    pb.setVisibility(View.GONE);
                    viewData();


                 }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });


        return results;
    }

    private void viewData() {
        recyclerView = findViewById(R.id.rv_countries_list);
        countryAdapter = new CountryAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Part05CountryListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
    }


}