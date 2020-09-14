package com.example.mvvmudemy01.view.part05;

import android.content.Intent;
import android.os.Bundle;

import com.example.mvvmudemy01.databinding.ActivityMovieDetailMvvmBinding;
import com.example.mvvmudemy01.model.part05.MovieMvvm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.View;

import com.example.mvvmudemy01.R;

public class MovieDetailMvvmActivity extends AppCompatActivity {

    private MovieMvvm movie;
    private ActivityMovieDetailMvvmBinding activityMovieDetailMvvmBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_mvvm);
        activityMovieDetailMvvmBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail_mvvm);
        Toolbar toolbar = activityMovieDetailMvvmBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {

            movie = getIntent().getParcelableExtra("movie");
            activityMovieDetailMvvmBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());


        }
    }
}