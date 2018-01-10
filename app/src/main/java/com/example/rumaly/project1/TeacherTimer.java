package com.example.rumaly.project1;

import android.util.Log;


/**
 * Created by Rumaly on 10-Nov-16.
 */

public class TeacherTimer implements NotificationHandler {
    final TimerController timee = new TimerController();

    public long setnotificationtime() {
        long millis = System.currentTimeMillis();
        millis += 60000;
        Log.d("teacherrrrrrrr", Long.toString(millis));
        return millis;

    }

    public void checkstate(){

    }




}
