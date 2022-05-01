package com.example.workshop_msap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyReceiver", "reset");
        context.startService(new Intent(context, MyService.class));
        //prvo dosega aktivniot servis zastanuva, i povtorno zapocnuva so cel da se resetira
    }
}
