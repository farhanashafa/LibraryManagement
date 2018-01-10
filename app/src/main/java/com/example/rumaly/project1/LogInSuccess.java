package com.example.rumaly.project1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class LogInSuccess extends AppCompatActivity{

    private Button issuedBooks;
    Button buttonNotificationShow, buttonNotificationClear;
    static NotificationManager notificationManager;
    static Notification mNotification;
    static PendingIntent mPendingIntent;
    public static Context context;
    private String post = "";
    private String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        context = getApplicationContext();
      

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                Log.d("LogInSuccess ", "UserID not found");
            }
            else {
                post =extras.getString("userPost"); //changed
                Log.d("GotPost", post);
            }
        }


        issuedBooks = (Button)findViewById(R.id.IssuedBooks);

        Button bt = (Button)findViewById(R.id.gotobooklist);
        Button logout=(Button)findViewById(R.id.log_out_button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInSuccess.this, viewType.class);
                i.putExtra("userPost",post);
                startActivity(i);
            }
        });

        final Runnable mMyRunnable = new Runnable()  //delay for logout
        {
            @Override
            public void run()
            {
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                Toast.makeText(LogInSuccess.this, "Successfully Logged Out", Toast.LENGTH_LONG).show();
            }
        };


       logout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Handler myHandler = new Handler();
                                          myHandler.postDelayed(mMyRunnable, 1000);
                                      }

                                  });




        initialise();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        buttonNotificationShow.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                SubmissionCheck submissionCheck = new SubmissionCheck();
                submissionCheck.setContext(getApplicationContext());
                submissionCheck.check(notificationManager);


            }
        });

        buttonNotificationClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                notificationManager.cancel(11);
            }
        });

        issuedBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogInSuccess.this, showIssuedBooks.class);
                startActivity(i);
            }
        });
    }

    private void initialise() {
        buttonNotificationShow = (Button) findViewById(R.id.notification_show_button);
        buttonNotificationClear = (Button) findViewById(R.id.notification_clear_button);
    }

    public static Context getContext()
    {
        return context;
    }

    public void onBackPressed()
    {
        finish();
        super.onBackPressed();
    }


}

