package com.example.mvvmudemy01.adapter.part05;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmudemy01.R;
import com.example.mvvmudemy01.databinding.MovieListItemMvvmBinding;
import com.example.mvvmudemy01.model.part05.Movie;
import com.example.mvvmudemy01.model.part05.MovieMvvm;
import com.example.mvvmudemy01.view.part05.MovieDetailActivity;
import com.example.mvvmudemy01.view.part05.MovieDetailMvvmActivity;

import java.util.ArrayList;

public class MovieAdapterMvvm extends RecyclerView.Adapter<MovieAdapterMvvm.MovieViewHolder>{

    private Context context;
    private ArrayList<MovieMvvm> movieArrayList;

    public MovieAdapterMvvm(Context context, ArrayList<MovieMvvm> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieListItemMvvmBinding movieListItemMvvmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
        ,R.layout.movie_list_item_mvvm,parent,false);

         return new MovieViewHolder(movieListItemMvvmBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        MovieMvvm movie=movieArrayList.get(position);

        holder.movieListItemMvvmBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieListItemMvvmBinding movieListItemMvvmBinding;

        public MovieViewHolder(MovieListItemMvvmBinding movieListItemMvvmBinding) {
            super(movieListItemMvvmBinding.getRoot());
            this.movieListItemMvvmBinding=movieListItemMvvmBinding;

            movieListItemMvvmBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        MovieMvvm selectedMovie=movieArrayList.get(position);
                        Intent intent = new Intent(context, MovieDetailMvvmActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });


        }
    }
}
