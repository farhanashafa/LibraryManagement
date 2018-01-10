package com.example.rumaly.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Israt on 11/4/2016.
 */

public class NotificationView extends Activity{

    private String bookname;
    private static String Path = "https://project1-4257e.firebaseio.com/BOOKS/";
    private TextView textView;
    private List<Book> bookList;
    private String TAG = "Mahi";
    private Button SubmitBook,decline;
    private String msg,bookid,uid;
    public static int submitFlag=2;
    Firebase firebase = new Firebase("https://project1-4257e.firebaseio.com/BOOKS/");
    Firebase blocked= new Firebase("https://project1-4257e.firebaseio.com/Blocked/");


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
        getIntent().removeExtra("bookname");
        getIntent().removeExtra("bookid");
        getIntent().removeExtra("uid");

    }


    @Override
    public void onNewIntent(Intent intent) {
        Bundle extras = getIntent().getExtras();

        for (String key : extras.keySet()) {
            Object value = extras.get(key);
            Log.d(TAG, String.format("%s %s (%s)", key,
                    value.toString(), value.getClass().getName()));
        }
        if (extras != null) {
            if (extras.containsKey("bookname"))
            {
                //Log.d("halum", bookname);
                setContentView(R.layout.notification);
                // extract the extra-data in the Notification
                msg = extras.getString("bookname");
                bookid = extras.getString("bookid");
                uid = extras.getString("uid");

            }
        }
    }

    public void  SubmitBook (View v){
        submitFlag=0;
        firebase.child(uid).child(bookid).removeValue();

        Log.d(TAG, uid+":::"+bookid+":::"+bookname);

        textView = (TextView) findViewById(R.id.text);
        textView.setText(msg+" is successfully submitted");


    }


   public void  decline(View v){
        submitFlag = 1;
       Random r = new Random(1000);
       Log.d("AMi flag","decline korechi");
       Log.d("myuid",uid);
       Firebase mrootref = new Firebase(
               "https://project1-4257e.firebaseio.com/BLOCKS/");
       Log.d("happy",mrootref.getKey());

       Map newBook = new HashMap();
       newBook.put(String.valueOf(r.nextInt()),uid);
       mrootref.updateChildren(newBook);



    }

    }