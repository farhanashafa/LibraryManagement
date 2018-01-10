package com.example.rumaly.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by hosneara on 10/19/16.
 */

public class SignUpSuccess extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupsuccess);

    }
    public void loginpreview(View v) {
        Intent i = new Intent(SignUpSuccess.this, MainActivity.class);
        startActivity(i);
    }
}