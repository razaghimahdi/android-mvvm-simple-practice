package com.example.mvvmudemy01.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.adapter.part05.MovieAdapterMvvm;
import com.example.mvvmudemy01.adapter.part05.MovieAdapterPaging;
import com.example.mvvmudemy01.databinding.ActivityPart06PagingBinding;
import com.example.mvvmudemy01.model.part05.MovieMvvm;
import com.example.mvvmudemy01.model.part06.MoviePaging;
import com.example.mvvmudemy01.viewmodel.part06.Part06MovieListPagingActivityViewModel;
import com.example.mvvmudemy01.viewmodel.pat05.Part05MovieListMvvmActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class Part06PagingActivity extends AppCompatActivity {

    private PagedList<MoviePaging> movies;
    private RecyclerView recyclerView;
    private MovieAdapterPaging movieAdapter;
    private ProgressBar pb;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Part06MovieListPagingActivityViewModel part05ViewModel;
    private ActivityPart06PagingBinding activityPart06PagingBinding;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part06_paging);
        activityPart06PagingBinding = DataBindingUtil.setContentView(this,R.layout.activity_part06_paging);


        pb = activityPart06PagingBinding.pbpaging;

        part05ViewModel = new ViewModelProvider(this).get(Part06MovieListPagingActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityPart06PagingBinding.swippaging;
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

            }
        });


    }


    private void getPopularMovies() {
/*
        part05ViewModel.getAllMovies().observe(this, new Observer<List<MoviePaging>>() {
            @Override
            public void onChanged(List<MoviePaging> moviesFromLiveData) {

                movies = (ArrayList<MoviePaging>) moviesFromLiveData;
                pb.setVisibility(View.GONE);
                showOnRecyclerView();
            }
        });*/

        part05ViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<MoviePaging>>() {
            @Override
            public void onChanged(PagedList<MoviePaging> movieFromLiveData) {
                movies=movieFromLiveData;
                pb.setVisibility(View.GONE);
                showOnRecyclerView();
            }
        });


    }

    private void showOnRecyclerView() {
        recyclerView = activityPart06PagingBinding.rvMoviepaging;
        //movieAdapter = new MovieAdapterPaging(this, movies);
        movieAdapter=new MovieAdapterPaging(this);
        movieAdapter.submitList(movies);

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