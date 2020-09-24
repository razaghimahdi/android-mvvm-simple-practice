package com.example.mvvmudemy01.model.part06;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mvvmudemy01.model.part05.MovieMvvm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDBResponsePaging implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalMovies;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<MoviePaging> Movies = null;
    public final static Creator<MovieDBResponsePaging> CREATOR = new Creator<MovieDBResponsePaging>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDBResponsePaging createFromParcel(Parcel in) {
            return new MovieDBResponsePaging(in);
        }

        public MovieDBResponsePaging[] newArray(int size) {
            return (new MovieDBResponsePaging[size]);
        }

    };

    protected MovieDBResponsePaging(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.Movies, (MoviePaging.class.getClassLoader()));
    }

    public MovieDBResponsePaging() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MoviePaging> getMovies() {
        return Movies;
    }

    public void setMovies(List<MoviePaging> Movies) {
        this.Movies = Movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalMovies);
        dest.writeValue(totalPages);
        dest.writeList(Movies);
    }

    public int describeContents() {
        return 0;
    }

}