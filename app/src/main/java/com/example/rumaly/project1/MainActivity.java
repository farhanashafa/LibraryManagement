package com.example.rumaly.project1;


import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.rumaly.project1.R.id.designation;


public class MainActivity extends AppCompatActivity {



    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    public static boolean check = false;

    private EditText email, pass;
    private Button signin, signUP;

    private String email1, name1, phone1,post;
    private static final String TAG = "MyActivity";
    public static String uid = "";
    public String mypost="";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<String> list = new ArrayList<String>();   //make this as field atribute
        list.add("Student");
        list.add("Teacher");
        list.add("Admin");
        Spinner dropdown = (Spinner)findViewById(designation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);

        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mypost = list.get(position);
                if(position ==1 )
                {
                    //showDialogue1();
                }

                else  if(position ==2 )
                {
                   // showDialogue2();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });





        if (savedInstanceState == null && com.example.rumaly.project1.signup.flag == 1) {
            Bundle extras = getIntent().getExtras();

            name1 = extras.getString("name");
            email1 = extras.getString("email");
            phone1 = extras.getString("phone");
            post=  extras.getString("post");
            //uid =

        }

        reference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.login);
        signUP = (Button) findViewById(R.id.signup);

    }

    public void SignUp(View v) {

        Intent i = new Intent(MainActivity.this, signup.class);
        startActivity(i);
    }


    private void dataEntry() {

        user u = user.getInstance();
        u.setEmail(email1);
        u.setName(name1);
        u.setPhone(phone1);
        u.setPost(post);

        Log.d("ifshita", email1);
        Log.d("ifshita", phone1);
        Log.d("ifshita", name1);
        Log.d("ifshita", post);

        reference.child(uid).setValue(u);

    }

    public void btnLogin_Click(View v) {
        if (TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(pass.getText())) {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        } else {


            final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...",
                    "Proccessing...", true);

            (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                uid = user.getUid();
                                if (signup.flag == 1) {
                                    dataEntry();
                                }


                                else {
                                    getData();
                                }


                                //signup.flag = 0;

                                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                if(mypost.equals("Admin")){
                                    Intent i = new Intent(MainActivity.this, Admin.class);
                                    startActivity(i);
                                }
                                else{
                                Intent i = new Intent(MainActivity.this,LogInSuccess.class);
                                i.putExtra("userPost", mypost);//changed

                                startActivity(i);
                                }

                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast
                                        .LENGTH_LONG).show();

                            }
                        }
                    });
        }

    }

    private void getData() {


            final NotificationManager notificationManager = (NotificationManager) getSystemService
                    (NOTIFICATION_SERVICE);

            Firebase firebase = new Firebase("https://project1-4257e.firebaseio.com/BOOKS/"+uid);
            Log.d("Address ",firebase.getKey());


            firebase.addChildEventListener(new com.firebase.client.ChildEventListener
                    () {
                @Override
                public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                    SubmissionCheck submissionCheck = new SubmissionCheck();
                    Log.d("OnCHildAdded", String.valueOf(check));
                    if(!check) {
                        //for (com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren())

                        Log.d(TAG, "NOPEEEE");
                        Log.d(TAG, dataSnapshot.getValue().toString());

                        String[] values = dataSnapshot.getValue().toString().split(",");
                        Log.d("Name111 ", values[0]);
                        Log.d("Name222 ", values[1]);

                        Book book = new Book(submissionCheck);
                        book.setBook(uid, dataSnapshot.getKey(), values[0], values[1]);


                        submissionCheck.setContext(getApplicationContext());
                        NotificationManager notificationManager = (NotificationManager) getSystemService
                                (NOTIFICATION_SERVICE);
                        submissionCheck.check(notificationManager);
                    }
                }

                @Override
                public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            firebase.addValueEventListener(new com.firebase.client.ValueEventListener() {

                @Override
                public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                    {
                        Log.d("OnDataChange1", String.valueOf(check));
                        if(check)
                        {
                            check = false;
                            SubmissionCheck submissionCheck = new SubmissionCheck();
                            for (com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren()) {
                                Log.d("Data1 ", bookshot.getValue().toString());

                                String[] values = bookshot.getValue().toString().split(",");
                                Log.d("Name1 ", values[0]);
                                Log.d("Name2 ", values[1]);

                                Book book = new Book(submissionCheck);
                                book.setBook(uid, bookshot.getKey(), values[0], values[1]);

                            }


                            submissionCheck.setContext(getApplicationContext());
                            NotificationManager notificationManager = (NotificationManager) getSystemService
                                    (NOTIFICATION_SERVICE);
                            submissionCheck.check(notificationManager);

                        }

                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Toast.makeText(MainActivity.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
                }
            });

        }


    /*private String getPost(){
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //uid = user.getUid();
        Log.d("UIDDDD", uid);
        //DatabaseReference firebase= FirebaseDatabase.getInstance().getReference();
        Firebase rumaly = new Firebase("https://project1-4257e.firebaseio.com/" +uid );

        rumaly.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren()) {
                    // Log.d("DataShafa ",bookshot.getValue().toString());
                    String myKey = bookshot.getKey().toString();
                    // Log.d("Data1 ",myKey);
                    if (myKey.equals("post")) {
                        postfun=bookshot.getValue().toString();
                        Log.d("Designation", myKey);
                        Log.d("I am a",postfun);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(MainActivity.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
            }
        });
        return postfun;
    }*/

    }

