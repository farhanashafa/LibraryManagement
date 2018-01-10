package com.example.rumaly.project1;

import android.util.Log;

/**
 * Created by Rumaly on 11-Nov-16.
 */

public class StudentTime implements NotificationHandler {
        final TimerController timee = new TimerController();

    public long setnotificationtime() {
        long millis = System.currentTimeMillis();
        millis += 30000;
        Log.d("student", Long.toString(millis));
        return millis;

    }
    public void checkstate(){

    }




}