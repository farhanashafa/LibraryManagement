package com.example.rumaly.project1;

/**
 * Created by Rumaly on 10-Nov-16.
 */

public class TimerController {
    public NotificationHandler getDesignation(String post ){
        if(post.equals("Teacher")){
            return new TeacherTimer();
        }
        else if (post.equals("Student")){
            return new StudentTime();
        }
        return null;
    }



    }

