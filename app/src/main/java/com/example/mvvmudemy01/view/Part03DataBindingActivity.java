package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.databinding.ActivityPart03DataBindingBinding;
import com.example.mvvmudemy01.model.databindingModel.ContactDatabinding;

public class Part03DataBindingActivity extends AppCompatActivity {

    private ActivityPart03DataBindingBinding activityPart03DataBindingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part03_data_binding);

        ContactDatabinding contactDatabinding = new ContactDatabinding("mahdi","mahdi@gmail.com");

        activityPart03DataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_part03_data_binding);
        activityPart03DataBindingBinding.setContactDatabinding(contactDatabinding);


    }
}