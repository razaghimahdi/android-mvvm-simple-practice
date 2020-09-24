package com.example.mvvmudemy01.model.part06;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.service.MovieDataServicePart06;
import com.example.mvvmudemy01.service.RetrofitInstanceMoviePaging;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSourcePart06 extends PageKeyedDataSource<Long, MoviePaging> {
    private MovieDataServicePart06 movieDataServicePart06;
    private Application application;

    public MovieDataSourcePart06(MovieDataServicePart06 movieDataServicePart06, Application application) {
        this.movieDataServicePart06 = movieDataServicePart06;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, MoviePaging> callback) {

        movieDataServicePart06 = RetrofitInstanceMoviePaging.getService();
        Call<MovieDBResponsePaging> call = movieDataServicePart06.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), 1);

        call.enqueue(new Callback<MovieDBResponsePaging>() {
            @Override
            public void onResponse(Call<MovieDBResponsePaging> call, Response<MovieDBResponsePaging> response) {
                MovieDBResponsePaging movieDBResponsePaging = response.body();
                ArrayList<MoviePaging> movies = new ArrayList<>();
                movies = (ArrayList<MoviePaging>) movieDBResponsePaging.getMovies();
                if (movieDBResponsePaging != null && movieDBResponsePaging.getMovies() != null) {

                callback.onResult(movies, null, (long) 2);


            }
            }

            @Override
            public void onFailure(Call<MovieDBResponsePaging> call, Throwable t) {

            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, MoviePaging> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, MoviePaging> callback) {

        movieDataServicePart06 = RetrofitInstanceMoviePaging.getService();
        Call<MovieDBResponsePaging> call = movieDataServicePart06.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key), params.key);

        call.enqueue(new Callback<MovieDBResponsePaging>() {
            @Override
            public void onResponse(Call<MovieDBResponsePaging> call, Response<MovieDBResponsePaging> response) {
                MovieDBResponsePaging movieDBResponsePaging = response.body();
                ArrayList<MoviePaging> movies = new ArrayList<>();


                if (movieDBResponsePaging != null && movieDBResponsePaging.getMovies() != null) {
                    movies = (ArrayList<MoviePaging>) movieDBResponsePaging.getMovies();

                    callback.onResult(movies, params.key + 1);

                }


            }

            @Override
            public void onFailure(Call<MovieDBResponsePaging> call, Throwable t) {

            }
        });


    }
}
