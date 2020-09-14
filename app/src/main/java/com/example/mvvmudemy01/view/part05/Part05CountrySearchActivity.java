package com.example.mvvmudemy01.view.part05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.model.part05.InfoNoListForRestResponse;
import com.example.mvvmudemy01.model.part05.Result;
import com.example.mvvmudemy01.service.GetCountryDataService;
import com.example.mvvmudemy01.service.RetrofitInstanceGroupkt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part05CountrySearchActivity extends AppCompatActivity {


    EditText countryCodeEditText;
    TextView countryNameTextView;
    Button submitButton;
    Button clearButton;

    String countryCode;
    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part05_country_search);

        countryCodeEditText = (EditText) findViewById(R.id.etCode);
        countryNameTextView = (TextView) findViewById(R.id.tvCountryName);
        submitButton = (Button) findViewById(R.id.btnSubmit);
        clearButton = (Button) findViewById(R.id.btnClear);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                countryCode = countryCodeEditText.getText().toString();

                GetCountryDataService getCountryDataService = RetrofitInstanceGroupkt.getService();


                Call<InfoNoListForRestResponse> call = getCountryDataService.getResultByAlpha2Code(countryCode);


                call.enqueue(new Callback<InfoNoListForRestResponse>() {
                    @Override
                    public void onResponse(Call<InfoNoListForRestResponse> call, Response<InfoNoListForRestResponse> response) {

                        InfoNoListForRestResponse info = response.body();

                        if(info !=null && info.getRestResponse() != null) {

                            result = info.getRestResponse().getResult();

                            if (result.getName()!=null){

                                countryNameTextView.setText(result.getName());
                            }else{
                                countryNameTextView.setText("No Country such this");
                            }


                            Log.i("TAG", "onResponse: "+response);

                        }

                    }

                    @Override
                    public void onFailure(Call<InfoNoListForRestResponse> call, Throwable t) {
                        Log.i("TAG", "onFailure: "+t.getMessage());

                    }
                });
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countryNameTextView.setText(" ");
                countryCodeEditText.setText(" ");
            }
        });

    }
}