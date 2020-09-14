package com.example.mvvmudemy01.service;

import com.example.mvvmudemy01.model.part05.Info;
import com.example.mvvmudemy01.model.part05.InfoNoListForRestResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetCountryDataService {


    @GET("country/get/all")
    Call<Info> getResults();

    @GET("country/get/iso2code/{alpha2_code}")
    Call<InfoNoListForRestResponse> getResultByAlpha2Code(@Path("alpha2_code") String alpha2Code);


}
