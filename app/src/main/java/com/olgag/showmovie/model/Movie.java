package com.olgag.showmovie.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie  implements Parcelable {
    private long idMovie;
    private float movieRating;
    private int movieReleaseYear;
    private String movietitle,urlimage,movieGenre;

    public Movie(long idMovie, float movieRating, int movieReleaseYear, String movietitle, String urlimage, String movieGenre) {
        this.idMovie = idMovie;
        this.movieRating = movieRating;
        this.movieReleaseYear = movieReleaseYear;
        this.movietitle = movietitle;
        this.urlimage = urlimage;
        this.movieGenre = movieGenre;
    }

    protected Movie(Parcel in) {
        idMovie = in.readLong();
        movieRating = in.readFloat();
        movieReleaseYear = in.readInt();
        movietitle = in.readString();
        urlimage = in.readString();
        movieGenre = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }

    public int getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(int movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", movieRating=" + movieRating +
                ", movieReleaseYear=" + movieReleaseYear +
                ", movietitle='" + movietitle + '\'' +
                ", urlimage='" + urlimage + '\'' +
                ", movieGenre=" + movieGenre +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idMovie);
        dest.writeFloat(movieRating);
        dest.writeInt(movieReleaseYear);
        dest.writeString(movietitle);
        dest.writeString(urlimage);
        dest.writeString(movieGenre);
    }
}
