package com.example.mvvmudemy01.service;

import com.example.mvvmudemy01.model.part05.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovie(@Query("api_key")String apiKey);

}
