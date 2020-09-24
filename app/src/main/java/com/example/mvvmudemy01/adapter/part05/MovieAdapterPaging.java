package com.example.mvvmudemy01.adapter.part05;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.databinding.MovieListItemMvvmBinding;
import com.example.mvvmudemy01.databinding.MovieListItemPagingBinding;
import com.example.mvvmudemy01.model.part06.MoviePaging;
import com.example.mvvmudemy01.view.part05.MovieDetailMvvmActivity;

import java.util.ArrayList;

public class MovieAdapterPaging extends PagedListAdapter<MoviePaging,MovieAdapterPaging.MovieViewHolder> {

    private Context context;
    //private ArrayList<MoviePaging> movieArrayList;

    public MovieAdapterPaging(Context context/*, ArrayList<MoviePaging> movieArrayList*/) {
        super(MoviePaging.CALLBACK);
        this.context = context;
        //this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemPagingBinding movieListItemMvvmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
        ,R.layout.movie_list_item_paging,parent,false);

         return new MovieViewHolder(movieListItemMvvmBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

       // MoviePaging movie=movieArrayList.get(position);
        MoviePaging movie=getItem(position);

        holder.movieListItemMvvmBinding.setMovie(movie);
    }
/*
    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }*/


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemPagingBinding movieListItemMvvmBinding;

        public MovieViewHolder(MovieListItemPagingBinding movieListItemMvvmBinding) {
            super(movieListItemMvvmBinding.getRoot());
            this.movieListItemMvvmBinding=movieListItemMvvmBinding;

            movieListItemMvvmBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        //MoviePaging selectedMovie=movieArrayList.get(position);
                        MoviePaging selectedMovie=getItem(position);
                        Intent intent = new Intent(context, MovieDetailMvvmActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        //context.startActivity(intent);
                    }
                }
            });


        }
    }
}
