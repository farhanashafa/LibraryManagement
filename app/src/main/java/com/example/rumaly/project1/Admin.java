package com.example.rumaly.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hosneara on 11/15/16.
 */

public class Admin extends AppCompatActivity {
    Button b1 ;
    Button b2 ;
    Button b3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_front_page);

        b1 = (Button)findViewById(R.id.addbook);
        b2 = (Button)findViewById(R.id.removebook);
        b3 = (Button)findViewById(R.id.viewissuer);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, newBookAdd.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, BookRemove.class);
                startActivity(i);
            }
        });
    }
}
