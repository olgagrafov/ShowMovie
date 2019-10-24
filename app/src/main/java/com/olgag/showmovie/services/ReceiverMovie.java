package com.olgag.showmovie.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverMovie extends BroadcastReceiver {
    private Context cont;
    private OnReceivedListener listener;

    public ReceiverMovie(Context cont) {
        this.cont = cont;
        this.listener = (OnReceivedListener) cont;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
      //  String recStr =  intent.getStringExtra("movielist");
      //  Toast.makeText(context, ":" + recStr, Toast.LENGTH_LONG).show();
        listener.startMainActivity();
    }
    public interface OnReceivedListener{
        void startMainActivity();
    }
}
