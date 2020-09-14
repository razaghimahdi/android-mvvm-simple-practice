package com.example.mvvmudemy01.view.part05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.model.part05.User;
import com.example.mvvmudemy01.service.PostAppService;
import com.example.mvvmudemy01.service.RetrofitInstanceJsonplaceholder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part05UserActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText passWord;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part05_user);


        userEmail = (EditText) findViewById(R.id.et_email);
        passWord = (EditText) findViewById(R.id.et_password);
        submitButton = (Button) findViewById(R.id.btn_submit);
        resultTextView = (TextView) findViewById(R.id.tv_result);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postData();
            }
        });

    }



    private void postData() {

        User user = new User();
        user.setUserEmail(userEmail.getText().toString());
        user.setPassWord(passWord.getText().toString());

        Log.i("responsetest", "******************* before id : " + user.getId());

        PostAppService postAppService = RetrofitInstanceJsonplaceholder.getService();
        Call<User> call = postAppService.getResult(user);

        userEmail.setText("");
        passWord.setText("");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                User returnedUser = response.body();

                resultTextView.setText("Id is : " + returnedUser.getId());

                Log.i("TAG",  "onResponse ******************* after id : " + returnedUser.getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });
    }



}