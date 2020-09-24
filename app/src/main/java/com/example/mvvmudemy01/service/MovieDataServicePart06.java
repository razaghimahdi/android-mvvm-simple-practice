package com.example.mvvmudemy01.service;

import com.example.mvvmudemy01.model.part05.MovieDBResponseMvvm;
import com.example.mvvmudemy01.model.part06.MovieDBResponsePaging;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataServicePart06 {

    @GET("movie/popular")
    Call<MovieDBResponsePaging> getPopularMovie(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDBResponsePaging> getPopularMoviesWithPaging(@Query("api_key") String apiKey,@Query("page") long page);


}
