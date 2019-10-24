package com.olgag.showmovie.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.olgag.showmovie.db.DbHelper;
import com.olgag.showmovie.db.DbProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServicesMovie extends IntentService {
    public static final String MOVIE_SERVICE = "com.olgag.showmovie.services.MOVIE_SERVICE";

    public ServicesMovie() {
        super("ServicesMovie");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String strUrlJSON = "https://api.androidhive.info/json/movies.json";
        HttpURLConnection connection = null;
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        URL url = null;

        String str="";

        try {
            url = new URL(strUrlJSON);
            connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return;
            }
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            if(!builder.toString().isEmpty()) {
                long idMovie;
                float movieRating;
                int movieReleaseYear;
                String movietitle,urlimage,movieGenre;
                ContentValues values = new ContentValues();
                getContentResolver().delete(DbProvider.MOVIE_TBL_URL, null, null);

                JSONArray movieArr = new JSONArray(builder.toString());
                for(int i=0;i<movieArr.length();i++){
                    movieGenre="";
                    JSONObject movie = new JSONObject(movieArr.get(i).toString());
                    {
                        movietitle= movie.getString("title");
                        urlimage= movie.getString("image");
                        movieRating = Float.parseFloat(movie.getString("rating"));
                        movieReleaseYear = Integer.parseInt(movie.getString("releaseYear"));
                        JSONArray jsnMovieGenre = new JSONArray(movie.getString("genre"));

                        for(int j=0;j<jsnMovieGenre.length();j++){
                            movieGenre= movieGenre + jsnMovieGenre.get(j).toString() + ", ";
                        }
                    }

                    values.put(DbHelper.MOVIE_TITLE,movietitle);
                    values.put(DbHelper.MOVIE_URL_IMAGE, urlimage);
                    values.put(DbHelper.MOVIE_RELEASE_YEAR, movieReleaseYear);
                    values.put(DbHelper.MOVIE_RATING,movieRating);
                    values.put(DbHelper.MOVIE_GENRE, movieGenre.substring(0,movieGenre.length()-2));

                    getContentResolver().insert(DbProvider.MOVIE_TBL_URL, values);

                 //   str=str + movieGenre + " " + movietitle + " " + urlimage + " " + movieRating + " " + movieReleaseYear;
                }

            }

            Intent broadIntent = new Intent(MOVIE_SERVICE);

           // broadIntent.putExtra("movielist",str );

            LocalBroadcastManager.getInstance(this).sendBroadcast(broadIntent);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
