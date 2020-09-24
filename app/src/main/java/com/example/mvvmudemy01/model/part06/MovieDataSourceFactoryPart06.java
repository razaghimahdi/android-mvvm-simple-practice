package com.example.mvvmudemy01.model.part06;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.mvvmudemy01.service.MovieDataServicePart06;

public class MovieDataSourceFactoryPart06 extends DataSource.Factory {

    private MovieDataServicePart06 movieDataService;
    private MovieDataSourcePart06 movieDataSource;
    private MutableLiveData<MovieDataSourcePart06> mutableLiveData;
    private Application application;


    public MovieDataSourceFactoryPart06(MovieDataServicePart06 movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource=new MovieDataSourcePart06(movieDataService,application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }


    public MutableLiveData<MovieDataSourcePart06> getMutableLiveData() {
        return mutableLiveData;
    }
}
