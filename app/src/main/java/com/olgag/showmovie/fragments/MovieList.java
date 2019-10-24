package com.olgag.showmovie.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.olgag.showmovie.R;
import com.olgag.showmovie.controller.MovieAdapter;
import com.olgag.showmovie.db.DbHelper;
import com.olgag.showmovie.db.DbProvider;
import com.olgag.showmovie.model.Movie;

import java.util.ArrayList;

public class MovieList extends Fragment {

    private OnChosenMovieClickListener listener;
    private MovieAdapter movieAdapter;


    public MovieList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnChosenMovieClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw=inflater.inflate(R.layout.fragment_movie_list, container, false);

        RecyclerView rccMovieList=vw.findViewById(R.id.rccMovieList);
        rccMovieList.setLayoutManager(new GridLayoutManager(vw.getContext(),1));

        movieAdapter = new MovieAdapter(vw.getContext());
        rccMovieList.setAdapter(movieAdapter);

        Cursor c = getContext().getContentResolver().query(DbProvider.MOVIE_TBL_URL, null, null, null, "MOVIE_RATING DESC");
        ArrayList<Movie> listMovies=new ArrayList<>();
        while (c.moveToNext()) {
            listMovies.add(new Movie(c.getLong(c.getColumnIndex(DbHelper.TBL_MOVIE_ID)),
                                     c.getFloat(c.getColumnIndex(DbHelper.MOVIE_RATING)),
                                     c.getInt(c.getColumnIndex(DbHelper.MOVIE_RELEASE_YEAR)),
                                     c.getString(c.getColumnIndex(DbHelper.MOVIE_TITLE)),
                                     c.getString(c.getColumnIndex(DbHelper.MOVIE_URL_IMAGE)),
                                     c.getString(c.getColumnIndex(DbHelper.MOVIE_GENRE))));
            }

      //  Toast.makeText(vw.getContext(),listMovies.toString(),Toast.LENGTH_LONG).show();

       movieAdapter.addAll(listMovies);

       return vw;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface OnChosenMovieClickListener{
        void  setMovie(Movie curentMovie);
    }

}
