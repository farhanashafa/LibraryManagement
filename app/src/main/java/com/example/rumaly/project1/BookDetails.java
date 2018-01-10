package com.example.rumaly.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

/**
 * Created by hosneara on 10/28/16.
 */

public class BookDetails extends AppCompatActivity{


    private TextView textViewBook;
    private TextView textViewAuthor;
    private RatingBar ratingBar;
    private String rating;
    private Button button;
    private int position;
    private String bookname;
    private String post="";
    private static long time1;
    private DatabaseReference databaseReference;
    final BookAdd bookAdd = new BookAdd();
    final TimerController timee = new TimerController();
    //String mypost= getPost1();

    boolean flag = false;


    private int total;
    private String author;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                //position = 0;
            }
            else {
                //position = extras.getInt("position");
                author = extras.getString("author");
                bookname = extras.getString("book");
                post=extras.getString("userPost");
                String S = extras.getString("totalBooks");
                Log.d("BooKTatal: ", S);
                total = Integer.parseInt(S);
            }
        }


        textViewBook = (TextView) findViewById(R.id.bookName);
        textViewAuthor = (TextView)findViewById(R.id.bookauthor);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        button = (Button)findViewById(R.id.submit);

        textViewAuthor.setText(author);
        textViewBook.setText(bookname);
      //  Log.d("DataNotif ",post);

        Random R = new Random();
        position = R.nextInt(100) + 1;

        databaseReference = FirebaseDatabase.getInstance().getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;

                if ( total == 0 ){
                    Toast.makeText(BookDetails.this, "This book is not available. Try Later...", Toast.LENGTH_LONG).show();
                }
                else {
                    NotificationHandler notif1 = timee.getDesignation(post);
                    time1 = notif1.setnotificationtime();
                    String hms = Long.toString(time1);
                    Toast.makeText(BookDetails.this, "submission: " + hms, Toast.LENGTH_LONG).show();

                    //Toast.makeText(BookDetails.this, "flag: " + flagValue, Toast.LENGTH_LONG).show();

                    bookAdd.setBook(bookname, position, hms);
                    rating = String.valueOf((int) ratingBar.getRating());
                    Toast.makeText(BookDetails.this, "Your Rating: " + rating, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


   /* private String getPost1(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        Log.d("UIDDDD", uid);
        //DatabaseReference firebase= FirebaseDatabase.getInstance().getReference();
        Firebase rumaly = new Firebase("https://project1-4257e.firebaseio.com/" +uid+"post");

        rumaly.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren()) {
                    // Log.d("DataShafa ",bookshot.getValue().toString());
                    String myKey = bookshot.getKey().toString();
                    // Log.d("Data1 ",myKey);
                    if (myKey.equals("post")) {
                        post=bookshot.getValue().toString();
                        Log.d("Designation", myKey);
                        Log.d("I am a",post);

                    }
                }

            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(BookDetails.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
            }
        });


        return post;



    }*/


}


 /*rumaly.addValueEventListener(new com.firebase.client.ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Log.d("COUNTER ", String.valueOf(snapshot.getChildrenCount()));
                        for (DataSnapshot bookshot : snapshot.getChildren()) {
                            Log.d("YYYYYYYY",bookshot.getKey().toString());
                            if (bookshot.getKey().toString().equals("post"))
                            {

                                // if(bookshot.getValue().toString().equals("Teacher")) {
                                // System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                                post1 = bookshot.getValue().toString();
                                Log.d("Ifshitaadd ", post1);

                            }
                        }
                    }
                    @Override public void onCancelled(FirebaseError error) { }
                });*/