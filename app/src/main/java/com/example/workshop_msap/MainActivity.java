package com.example.workshop_msap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    Intent serviceIntent;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        myService=new MyService(getApplicationContext());
        serviceIntent = new Intent(getApplicationContext(), myService.getClass());
        if (!ServiceRunning(myService.getClass())) {
            startService(serviceIntent); //proveruvame dali servisot e aktiven, ako ne e go zapocnuvame
        }
        else {
            stopService(serviceIntent); //dokolku servisot e aktiven od prethodno postoenje na aplikacijata,
            // da se resetira so pocnuvanje od pocetok
        }
        finish();
    }

    private boolean ServiceRunning(Class<? extends MyService> aClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (aClass.getName().equals(service.service.getClassName())) {
                Log.i ("myService", "e aktiven");
                return true;
            }
        }
        Log.i ("myService", "NE e aktiven");
        return false;
    }

    @Override
    protected void onDestroy() {
        stopService(serviceIntent);
        // So stopiranje na servisot, toj ke ja povika svojata onDestroy
        //shto ke go povika da se kreira odnovo od koga aplikacijata e dead.
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();
    }
}