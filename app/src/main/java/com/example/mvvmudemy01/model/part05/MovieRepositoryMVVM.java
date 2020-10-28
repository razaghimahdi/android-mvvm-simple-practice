package com.example.mvvmudemy01.model.part05;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.model.part05.Movie;
import com.example.mvvmudemy01.model.part05.MovieDBResponse;
import com.example.mvvmudemy01.service.MovieDataService;
import com.example.mvvmudemy01.service.MovieDataServiceMvvm;
import com.example.mvvmudemy01.service.RetrofitInstanceMovie;
import com.example.mvvmudemy01.service.RetrofitInstanceMovieMvvm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryMVVM {



    private ArrayList<MovieMvvm> movies = new ArrayList<>();
    private MutableLiveData<List<MovieMvvm>> mutableLiveData = new MutableLiveData<>();
    private Application application;/**NOTE: how to get instance of application to repository?
     * we can create a constructor for this repository class and keeping application as a parameter.
     * Then we can use that application instance to get the string value of the API key.
     * */

    public MovieRepositoryMVVM(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<MovieMvvm>> getMutableLiveData(){
        MovieDataServiceMvvm movieDataService = RetrofitInstanceMovieMvvm.getService();

        Call<MovieDBResponseMvvm> call = movieDataService.getPopularMovie(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieDBResponseMvvm>() {
            @Override
            public void onResponse(Call<MovieDBResponseMvvm> call, Response<MovieDBResponseMvvm> response) {
                MovieDBResponseMvvm movieDBResponse=response.body();

                if (movieDBResponse!=null && movieDBResponse.getMovies()!=null){

                     movies=(ArrayList<MovieMvvm>)movieDBResponse.getMovies();
                    for (MovieMvvm m :
                            movies) {
                        Log.i("TAG", "onResponse: "+m);
                    }

                    mutableLiveData.setValue(movies);

                }


            }

            @Override
            public void onFailure(Call<MovieDBResponseMvvm> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage() );

            }
        });

        return mutableLiveData;
    }




}
