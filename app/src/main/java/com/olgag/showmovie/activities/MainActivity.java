package com.olgag.showmovie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.olgag.showmovie.R;
import com.olgag.showmovie.controller.MovieAdapter;
import com.olgag.showmovie.fragments.MovieList;
import com.olgag.showmovie.model.Movie;


public class MainActivity extends AppCompatActivity implements MovieList.OnChosenMovieClickListener, MovieAdapter.OnMovieClickListener {

    private MovieList frgMoviList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        frgMoviList = (MovieList) getSupportFragmentManager().findFragmentByTag("moviListFRG");

        if(savedInstanceState == null){
            frgMoviList=new MovieList();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer,frgMoviList,"moviListFRG")
                    .show(frgMoviList)
                    .commit();

        }

    }


    @Override
    public void setMovie(Movie curentMovie) {
       // Toast.makeText(this, ":" + curentMovie.getUrlimage(), Toast.LENGTH_SHORT).show();

       Intent intent = new Intent(this, MovieDetails.class);
       intent.putExtra("movielist", curentMovie);
       startActivity(intent);

    }
}
