package com.example.mvvmudemy01.model.part06;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.service.MovieDataServicePart06;
import com.example.mvvmudemy01.service.RetrofitInstanceMoviePaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryPaging {

    private ArrayList<MoviePaging> movies = new ArrayList<>();
    private MutableLiveData<List<MoviePaging>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepositoryPaging(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<MoviePaging>> getMutableLiveData(){


        MovieDataServicePart06 movieDataService = RetrofitInstanceMoviePaging.getService();

        Call<MovieDBResponsePaging> call = movieDataService.getPopularMovie(application.getApplicationContext().getString(R.string.api_key));


        call.enqueue(new Callback<MovieDBResponsePaging>() {
            @Override
            public void onResponse(Call<MovieDBResponsePaging> call, Response<MovieDBResponsePaging> response) {
                MovieDBResponsePaging movieDBResponse=response.body();

                if (movieDBResponse!=null && movieDBResponse.getMovies()!=null){

                     movies=(ArrayList<MoviePaging>)movieDBResponse.getMovies();
                    for (MoviePaging m :
                            movies) {
                        Log.i("TAG", "onResponse: "+m);
                    }

                    mutableLiveData.setValue(movies);

                }


            }

            @Override
            public void onFailure(Call<MovieDBResponsePaging> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage() );

            }
        });




        return mutableLiveData;
    }




}
