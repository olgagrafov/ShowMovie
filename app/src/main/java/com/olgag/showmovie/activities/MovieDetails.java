package com.olgag.showmovie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olgag.showmovie.R;
import com.olgag.showmovie.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
private Movie movie;
private TextView txtNameMovie,txtRaitingMovie,txtReleaseYear,txtGenre;
private ImageView imgPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie=intent.getParcelableExtra("movielist");
        //Toast.makeText(this, movie.toString(),Toast.LENGTH_LONG).show();
        txtNameMovie=findViewById(R.id.txtNameMovie);
        txtNameMovie.setText(movie.getMovietitle());
        txtRaitingMovie=findViewById(R.id.txtRaitingMovie);
        txtRaitingMovie.setText(movie.getMovieRating()+"");
        txtReleaseYear=findViewById(R.id.txtReleaseYear);
        txtReleaseYear.setText(movie.getMovieReleaseYear() +"");
        txtGenre=findViewById(R.id.txtGenre);
        txtGenre.setText(movie.getMovieGenre());
        imgPic=findViewById(R.id.imgPic);
        Picasso.get().load(movie.getUrlimage()).into(imgPic);

    }
}
