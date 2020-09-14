package com.example.mvvmudemy01.service;

import com.example.mvvmudemy01.model.part05.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostAppService {


    @POST("posts")
    Call<User> getResult(@Body User user);

}
