package com.olgag.showmovie.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.olgag.showmovie.R;
import com.olgag.showmovie.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private ArrayList<Movie> arrListMovie = new ArrayList<>();
    private int position;
    private OnMovieClickListener listener;

    public MovieAdapter(Context context) {
        this.context = context;
        this.listener = (OnMovieClickListener) context;
    }

    public void add(Movie movie){
        arrListMovie.add(movie);
        notifyItemInserted(arrListMovie.size()-1);
    }

    public void addAll(ArrayList<Movie> movie){
       this.arrListMovie.addAll(movie);
    }

    public void del(Movie movie){
        arrListMovie.remove(movie);
        notifyItemRemoved(position);
    }

    public void delAll(){
        int size = arrListMovie.size();
        arrListMovie.clear();
        notifyItemRangeRemoved(0, size);

    }
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(arrListMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return arrListMovie.size();
    }


    //**********************HOLDER********************************************
    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtMovieTitle,txtMovieRating;
        private Movie curentMovie;
        private LinearLayout layoutMovie;

        public MovieHolder(View itemView) {
            super(itemView);

            this.txtMovieTitle = itemView.findViewById(R.id.txtMovieTitle);
            txtMovieTitle.setOnClickListener(this);
            this.txtMovieRating = itemView.findViewById(R.id.txtMovieRating);
            txtMovieRating.setOnClickListener(this);
            this.layoutMovie= itemView.findViewById(R.id.rowMovie);
            layoutMovie.setOnClickListener(this);

        }

        public void bind(Movie movie) {
            curentMovie = movie;
            txtMovieTitle.setText(curentMovie.getMovietitle());
            txtMovieRating.setText(curentMovie.getMovieRating() + " ");
        }

        @Override
        public void onClick(View view) {
            listener.setMovie(curentMovie);
        }
    }


    public interface OnMovieClickListener{
        void setMovie(Movie curentMovie);
    }

}
