package com.example.mvvmudemy01.view.part08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.net.Uri;
import android.os.Bundle;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.view.part08.ui.main.Part08NavigationFragment;
import com.example.mvvmudemy01.view.part08.ui.main.Part08NavigationSecondFragment;

public class Part08NavigationActivity extends AppCompatActivity implements Part08NavigationSecondFragment.OnFragmentInteractionListener{

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part08_navigation_activity);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}