package com.example.rumaly.project1;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.firebase.client.Firebase;

import java.util.Random;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    String bookid, uID, submission;
    long timer;

    Firebase firebase = new Firebase("https://project1-4257e.firebaseio.com/BOOKS/");
    Random rand = new Random();
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this)
        {
            submission = intent.getExtras().getString("submit");
            timer = Long.parseLong(submission) - System.currentTimeMillis();
            if(timer > 0)
            try {
                wait(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(intent != null) {
            if (intent.hasExtra("bookname")) {
                String text = intent.getExtras().getString("bookname");
                bookid = intent.getExtras().getString("bookid");
                uID = intent.getExtras().getString("uid");
                showText(text);
            }
        }
    }
    private void showText(String text){
        NotificationView.submitFlag=0;
        int rn = rand.nextInt();
        Intent intent = new Intent(Book.getContext(), NotificationView.class);
        intent.putExtra("bookname", text);
        intent.putExtra("bookid",bookid);
        intent.putExtra("uid",uID);
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(Book.getContext());
        //stackBuilder.addParentStack(BookList.class);
        Log.d("Nothing", text);
        PendingIntent pendingIntent = PendingIntent.getActivity(Book.getContext(), rn, intent,
                PendingIntent
                .FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Notification").setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setContentText(text).build();
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context
                .NOTIFICATION_SERVICE);
        notificationManager.notify(rn, notification);
    }
}
