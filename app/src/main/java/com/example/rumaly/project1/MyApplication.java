package com.example.rumaly.project1;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by hosneara on 11/2/16.
 */


    public class MyApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            Firebase.setAndroidContext(this);
        }
    }

