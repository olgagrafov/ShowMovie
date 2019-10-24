package com.olgag.showmovie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.olgag.showmovie.R;
import com.olgag.showmovie.services.ReceiverMovie;
import com.olgag.showmovie.services.ServicesMovie;

public class SplashActivity extends AppCompatActivity implements ReceiverMovie.OnReceivedListener  {
    private ReceiverMovie movieReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intC = new Intent(this, ServicesMovie.class);
        this.startService(intC);
        movieReceiver = new ReceiverMovie(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(movieReceiver, new IntentFilter(ServicesMovie.MOVIE_SERVICE));

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(movieReceiver);
    }


    @Override
    public void startMainActivity() {

        //Toast.makeText(this, ":" + "SplashActivity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
