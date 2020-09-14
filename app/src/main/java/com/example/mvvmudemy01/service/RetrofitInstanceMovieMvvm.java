package com.example.mvvmudemy01.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceMovieMvvm {


    private static Retrofit retrofit = null;
    private static String BASE_URL="https://api.themoviedb.org/3/";

    public static MovieDataServiceMvvm getService() {


        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return retrofit.create(MovieDataServiceMvvm.class);
    }


}
