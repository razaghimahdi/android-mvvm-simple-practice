package com.example.mvvmudemy01.model.part05;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDBResponseMvvm implements Parcelable {

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
    private List<MovieMvvm> Movies = null;
    public final static Creator<MovieDBResponseMvvm> CREATOR = new Creator<MovieDBResponseMvvm>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDBResponseMvvm createFromParcel(Parcel in) {
            return new MovieDBResponseMvvm(in);
        }

        public MovieDBResponseMvvm[] newArray(int size) {
            return (new MovieDBResponseMvvm[size]);
        }

    };

    protected MovieDBResponseMvvm(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.Movies, (MovieMvvm.class.getClassLoader()));
    }

    public MovieDBResponseMvvm() {
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

    public List<MovieMvvm> getMovies() {
        return Movies;
    }

    public void setMovies(List<MovieMvvm> Movies) {
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