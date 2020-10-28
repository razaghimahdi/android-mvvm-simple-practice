package com.example.mvvmudemy01.viewmodel.part06;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.mvvmudemy01.model.part05.Movie;
import com.example.mvvmudemy01.model.part05.MovieMvvm;
import com.example.mvvmudemy01.model.part05.MovieRepositoryMVVM;
import com.example.mvvmudemy01.model.part06.MovieDataSourceFactoryPart06;
import com.example.mvvmudemy01.model.part06.MovieDataSourcePart06;
import com.example.mvvmudemy01.model.part06.MoviePaging;
import com.example.mvvmudemy01.model.part06.MovieRepositoryPaging;
import com.example.mvvmudemy01.service.MovieDataServicePart06;
import com.example.mvvmudemy01.service.RetrofitInstanceMoviePaging;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Part06MovieListPagingActivityViewModel extends AndroidViewModel {
    private MovieRepositoryPaging movieRepository;

    LiveData<MovieDataSourcePart06> movieDataSourcePart06LiveData;
    private Executor executor;
    private LiveData<PagedList<MoviePaging>> pagedListLiveData;

    public Part06MovieListPagingActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository=new MovieRepositoryPaging(application);

        MovieDataServicePart06 movieDataService = RetrofitInstanceMoviePaging.getService();
        MovieDataSourceFactoryPart06 factory = new MovieDataSourceFactoryPart06(movieDataService,application);
        movieDataSourcePart06LiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())/**NOTE: PagedList is a special list we create when we are using paging library to hold our data items.*/
                .setEnablePlaceholders(true)/**NOTE: if we want to have a placeholder that is visible to the user till the data is fully loaded*/
                .setInitialLoadSizeHint(10)/**NOTE: the number of items to load initially can be defined using setInitialLoadSizeHint.*/
                .setPageSize(20)/**the number of items to load in the PagedList can be defined using setPageSize*/
                .setPrefetchDistance(4)/**Number of pages to load initially when the screen loads can be defined using setPreFetchDistance.*/
                .build();

        executor = Executors.newFixedThreadPool(5);

        pagedListLiveData = (new LivePagedListBuilder<Long,MoviePaging>(factory,config))
                .setFetchExecutor(executor)
                .build();



    }

    public LiveData<PagedList<MoviePaging>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public LiveData<List<MoviePaging>> getAllMovies(){
        return movieRepository.getMutableLiveData();
    }

}
