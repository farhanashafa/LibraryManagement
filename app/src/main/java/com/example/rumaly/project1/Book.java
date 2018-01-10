package com.example.rumaly.project1;

import android.content.Context;
import android.content.Intent;

/**
 * Created by hosneara on 11/4/16.
 */

public class Book {

    private static Context mcontext;
    public static final int TRAY_ID = 23786484;
    private static int i = 0;
    private static int flag = 0;
    private String uID;
    private String id;

    private String bookname;
    private String submit;
    protected SubmissionCheck submissionCheck;
    Book(SubmissionCheck submissionCheck)
    {
        this.submissionCheck = submissionCheck;
        this.submissionCheck.attachBook(this);
    }
    public String getName()
    {
        return this.bookname;
    }
    public void setBook(String uid, String id, String bookname, String submit)
    {
        this.id = id;
        this.bookname = bookname;
        this.submit = submit;
        uID = uid;
    }
    public String getSubmissionTime()
    {
        return this.submit;
    }
    public static Context getContext()
    {
        return mcontext;
    }

    public void update()
    {
        mcontext = submissionCheck.getContext();
        Intent intent = new Intent(mcontext, MyIntentService.class);
        intent.putExtra("bookname",this.bookname);
        intent.putExtra("bookid",this.id);
        intent.putExtra("uid",this.uID);
        intent.putExtra("submit", this.submit);
        mcontext.startService(intent);
        /*Random rand = new Random();
        int fixed = rand.nextInt();
        mcontext = submissionCheck.getContext();
        NotificationManager notificationManager = submissionCheck.getMnotificationManager();
        Notification mNotification ;
        PendingIntent mPendingIntent;
        Intent intent = new Intent(mcontext, NotificationView.class);
        intent.putExtra("bookname",this.bookname);
        intent.putExtra("bookid",this.id);
        intent.putExtra("uid",this.uID);
        Log.d("habibi",this.bookname+" "+i);
        //intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mPendingIntent = PendingIntent.getActivity(mcontext, fixed, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification.Builder mBuilder = new Notification.Builder(mcontext);

        mBuilder.setAutoCancel(true);
        mBuilder.setContentTitle("Android App Notification");
        mBuilder.setTicker("Notification!!!");
        //mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setContentText(this.bookname);
        mBuilder.setSmallIcon(R.drawable.notification);
        mBuilder.setContentIntent(mPendingIntent);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setOngoing(true);
        //API level 16
        mBuilder.setSubText(this.id);
        mBuilder.setNumber(150);
        //mBuilder.build();
        //mNotification = mBuilder.getNotification();
        //notificationManager.notify(11, mNotification);

        //mcontext = submissionCheck.getContext();
        Log.d("!!!Hello User!!!", "Submit: "+this.bookname);
        //Intent i = new Intent(mcontext, NotificationView.class);

        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //mcontext.startActivity(i);


        mBuilder.build();

        mNotification = mBuilder.getNotification();
        //mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        //notificationManager.cancel( fixed );
        notificationManager.notify(fixed, mNotification);*/
    }

}
