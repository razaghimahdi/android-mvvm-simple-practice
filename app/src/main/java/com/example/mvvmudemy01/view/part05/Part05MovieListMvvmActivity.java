package com.example.mvvmudemy01.view.part05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.part05.MovieAdapter;
import com.example.mvvmudemy01.adapter.part05.MovieAdapterMvvm;
import com.example.mvvmudemy01.databinding.ActivityPart05MovieListMvvmBinding;
import com.example.mvvmudemy01.model.part05.Movie;
import com.example.mvvmudemy01.model.part05.MovieDBResponse;
import com.example.mvvmudemy01.model.part05.MovieMvvm;
import com.example.mvvmudemy01.service.MovieDataService;
import com.example.mvvmudemy01.service.RetrofitInstanceMovie;
import com.example.mvvmudemy01.viewmodel.Part04ViewModel;
import com.example.mvvmudemy01.viewmodel.pat05.Part05MovieListMvvmActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part05MovieListMvvmActivity extends AppCompatActivity {

    private ArrayList<MovieMvvm> movies;
    private RecyclerView recyclerView;
    private MovieAdapterMvvm movieAdapter;
    private ProgressBar pb;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Part05MovieListMvvmActivityViewModel part05ViewModel;
    private ActivityPart05MovieListMvvmBinding activityPart05MovieListMvvmBinding;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part05_movie_list_mvvm);
        activityPart05MovieListMvvmBinding = DataBindingUtil.setContentView(this,R.layout.activity_part05_movie_list_mvvm);


        pb = activityPart05MovieListMvvmBinding.pbmvvm;

        part05ViewModel = new ViewModelProvider(this).get(Part05MovieListMvvmActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityPart05MovieListMvvmBinding.swipmvvm;
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

            }
        });

    }

    private void getPopularMovies() {

        part05ViewModel.getAllMovies().observe(this, new Observer<List<MovieMvvm>>() {
            @Override
            public void onChanged(List<MovieMvvm> moviesFromLiveData) {

                movies = (ArrayList<MovieMvvm>) moviesFromLiveData;
                pb.setVisibility(View.GONE);
                showOnRecyclerView();
            }
        });


    }

    private void showOnRecyclerView() {
        recyclerView = activityPart05MovieListMvvmBinding.rvMoviemvvm;
        movieAdapter = new MovieAdapterMvvm(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }


}