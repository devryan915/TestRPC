package com.example.ryan.testrpcserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ServerReceiver extends BroadcastReceiver {

    private static final String TAG = ServerReceiver.class.getSimpleName();

    public ServerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String aciton = intent.getAction();
        String value = intent.getStringExtra("value");
        Log.d(TAG, aciton);
        Toast.makeText(context, "ServerReceive:" + aciton + " " + value, Toast.LENGTH_SHORT).show();
    }
}
