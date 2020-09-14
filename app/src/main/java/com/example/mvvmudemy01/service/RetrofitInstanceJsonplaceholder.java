package com.example.mvvmudemy01.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by K. A. ANUSHKA MADUSANKA on 6/25/2018.
 */
public class RetrofitInstanceJsonplaceholder {

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static PostAppService getService() {

        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }

        return retrofit.create(PostAppService.class);
    }
}
