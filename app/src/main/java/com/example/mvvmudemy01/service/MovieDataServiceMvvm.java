package com.example.mvvmudemy01.service;

import com.example.mvvmudemy01.model.part05.MovieDBResponse;
import com.example.mvvmudemy01.model.part05.MovieDBResponseMvvm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataServiceMvvm {

    @GET("movie/popular")
    Call<MovieDBResponseMvvm> getPopularMovie(@Query("api_key") String apiKey);

}
