package com.example.mvvmudemy01.view.part05;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.mvvmudemy01.model.part05.Movie;
import com.example.mvvmudemy01.model.part05.MovieDBResponse;
import com.example.mvvmudemy01.service.MovieDataService;
import com.example.mvvmudemy01.service.RetrofitInstanceMovie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part05MovieListActivity extends AppCompatActivity {


    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    private ProgressBar pb;

    private SwipeRefreshLayout swipeRefreshLayout;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part05_movie_list);

        pb = findViewById(R.id.pb);

        getPopularMovies();

        swipeRefreshLayout = findViewById(R.id.swip);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();

            }
        });

    }

    private void getPopularMovies() {

        MovieDataService movieDataService = RetrofitInstanceMovie.getService();

        Call<MovieDBResponse> call = movieDataService.getPopularMovie(this.getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse=response.body();

                if (movieDBResponse!=null && movieDBResponse.getMovies()!=null){

                    pb.setVisibility(View.GONE);
                    movies=(ArrayList<Movie>)movieDBResponse.getMovies();
                    for (Movie m :
                            movies) {
                        Log.i("TAG", "onResponse: "+m);
                    }
                    showOnRecyclerView();


                }


            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage() );
                pb.setVisibility(View.GONE);

            }
        });

    }

    private void showOnRecyclerView(){
        recyclerView = findViewById(R.id.rvMovie);
        movieAdapter = new MovieAdapter(this,movies);

        if (this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }




}