package com.example.rumaly.project1;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hosneara on 11/2/16.
 */

public class SubmissionCheck {
    public static  Timer timer = new Timer();

    private boolean state[];
    //int in=0;
    private NotificationManager mnotificationManager;
    private String submissionTime;
    private static List<Book> books;
    private Context mcontext;
    public void setContext(Context context)
    {
        mcontext = context;
    }
    public Context getContext()
    {
        return mcontext;
    }
    public NotificationManager getMnotificationManager()
    {
        return mnotificationManager;
    }
    SubmissionCheck()
    {
        state = new boolean[100];
        books = new ArrayList<Book>();
    }
    public static List<Book> getBooks()
    {
        return books;
    }
    public static void setState(int index)
    {
        //state[index] = true;
        notifyObserver(index);
    }
    public static void notifyObserver(int index)
    {
        Log.d("!!!Hello User!!!" , "Submit "+books.get(index).getName());
        books.get(index).update();
        books.remove(index);
    }
    public static void attachBook(Book book)
    {
        books.add(book);
    }
    public void check(NotificationManager notificationManager)
    {
        this.mnotificationManager = notificationManager;
        Log.d("Inside Booklist ",String.valueOf(books.size()));
        for(int i=0; i<books.size(); i++)
        {
            setState(i);
          /*  long Curtime = System.currentTimeMillis();
            //Log.d("Hope1 : ",Long.toString(Curtime));
            long prevtime = Long.parseLong(books.get(i).getSubmissionTime());
            if(Curtime >= prevtime)
            {
                setState(i);
                //Log.d("Hope2: ", "Time out");
            }
            else {
                    timer = new Timer();
                    timer.schedule(new childTimer(i), (prevtime-Curtime));
            } */
        }
    }
}
 class childTimer extends TimerTask{
     private int index;
     childTimer(int i)
     {
         index = i;
     }
    @Override
     public void run()
    {
        SubmissionCheck.setState(index);
        SubmissionCheck.timer.cancel();
        SubmissionCheck.timer.purge();
    }
}
