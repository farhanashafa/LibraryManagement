package com.example.rumaly.project1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TeacherTimer1 extends AppCompatActivity implements NotificationHandler {
    final TimerController timee = new TimerController();
    EditText date;
    DatePickerDialog datePickerDialog;
    String str;
   static long millis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_timer1);
        gettime();

    }


    private void gettime() {
       /* long millis = System.currentTimeMillis();
        millis += milliseconds;
        Log.d("teacherrrrrrrr", Long.toString(millis));
        Toast.makeText(this, "submission: "+millis, Toast.LENGTH_LONG).show();
        return millis;*/
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(TeacherTimer1.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                str = date.getText().toString();
                                Toast.makeText(TeacherTimer1.this, "date: "+str, Toast.LENGTH_LONG).show();
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                String dateInString = str;
                                Date date2 = null;
                                try {
                                    date2 = formatter.parse(dateInString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                millis= date2.getTime();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                Intent i = new Intent(TeacherTimer1.this, BookDetails.class);
                startActivity(i);

                // Toast.makeText(this, "Time is set: "+str, Toast.LENGTH_LONG).show();
            }
        });



    }
    public long setnotificationtime(){
        return millis;

    }

    public void checkstate(){

    }

}
