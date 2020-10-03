package com.example.mvvmudemy01.view.part08.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmudemy01.R;


public class Part08NavigationFragment extends Fragment {

    private Part08NavigationViewModel mViewModel;

    public static Part08NavigationFragment newInstance() {
        return new Part08NavigationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.part08_navigation_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Part08NavigationViewModel.class);

        EditText editText=getView().findViewById(R.id.nameText);

        Button button=getView().findViewById(R.id.mainButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(Part08NavigationFragmentDirections.mainToSecond()/*.setUserName(editText.getText().toString())*/);
            }
        });



    }

}