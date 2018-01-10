package com.example.rumaly.project1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static com.example.rumaly.project1.R.id.designation;


/**
 * Created by hosneara on 10/18/16.
 */

public class signup extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private EditText name, email, pass, phone;
    public static int flag = 0;
    public static int count=0;
    private  String post;
    Button send;
    String IDS;
    String teacher, admin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Log.d("Ref", "db ref: "+reference);
        firebaseAuth = FirebaseAuth.getInstance();
        name = (EditText)findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);

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
               post = list.get(position);
                if(position ==1 )
                {
                    showDialogue1();
                }

                else  if(position ==2 )
                {
                    showDialogue2();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


    }



    public void showDialogue1()
    {
        final LinearLayout item = (LinearLayout) findViewById(R.id.linear);
        final View v = View.inflate(getApplicationContext(),R.layout.dialogue,null);

        send = (Button) v.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                        "Checking...", true);

                EditText teacherID = (EditText) findViewById(R.id.input);
                teacher = teacherID.getText().toString();
               // Toast.makeText(getApplicationContext(),"I caught you"+teacher ,Toast.LENGTH_LONG).show();
                Firebase ref = new Firebase("https://project1-4257e.firebaseio.com/Teacher");
                ref.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                    @Override
                    public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                        for (com.firebase.client.DataSnapshot idshot : dataSnapshot.getChildren()) {

                            String ID = idshot.getValue().toString();
                            Log.d("Ifshitacheck", ID);

                            if(ID.equals(teacher)) {

                                progressDialog.dismiss();
                                Log.d("Ifshitainside", ID);
                                count=1;
                                break;
                            }
                            else count=0;



                        }
                        if(count==1){


                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
                            item.removeView(v);
                        }
                        else if(count==0){

                            Intent i = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"Invalid Id",Toast.LENGTH_LONG).show();
                            //showDialogue();
                        }


                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(signup.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        item.addView(v);
    }

    public void showDialogue2()
    {
        final LinearLayout item = (LinearLayout) findViewById(R.id.linear);
        final View v = View.inflate(getApplicationContext(),R.layout.dialogue,null);

        send = (Button) v.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                        "Checking...", true);

                EditText teacherID = (EditText) findViewById(R.id.input);
                admin = teacherID.getText().toString();
                // Toast.makeText(getApplicationContext(),"I caught you"+teacher ,Toast.LENGTH_LONG).show();
                Firebase ref = new Firebase("https://project1-4257e.firebaseio.com/Admin");
                ref.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                    @Override
                    public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                        for (com.firebase.client.DataSnapshot idshot : dataSnapshot.getChildren()) {

                            String ID = idshot.getValue().toString();
                            Log.d("Admincheck", ID);

                            if(ID.equals(admin)) {

                                progressDialog.dismiss();
                                Log.d("Ifshitainside", ID);
                                count=1;
                                break;
                            }
                            else count=0;



                        }
                        if(count==1){


                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
                            item.removeView(v);
                        }
                        else if(count==0){

                            Intent i = getBaseContext().getPackageManager()
                                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"Invalid Id",Toast.LENGTH_LONG).show();
                            //showDialogue();
                        }


                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(signup.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        item.addView(v);
    }


    public void btnRegistration_Click (View v){
        {   final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {

                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG)
                                        .show();

                                Intent i = new Intent(signup.this, MainActivity.class);


                                i.putExtra("name", name.getText().toString());
                                i.putExtra("email", email.getText().toString());
                                i.putExtra("phone", phone.getText().toString());
                               // i.putExtra("post", post.get().toString());
                                i.putExtra("post",post);
                                startActivity(i);

                                flag = 1;
                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast
                                        .LENGTH_LONG).show();
                            }

                        }
                    });

        }


    }

}


 /*public void processing(){

        {   final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {

                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG)
                                        .show();

                                Intent i = new Intent(signup.this, MainActivity.class);


                                i.putExtra("name", name.getText().toString());
                                i.putExtra("email", email.getText().toString());
                                i.putExtra("phone", phone.getText().toString());
                               // i.putExtra("post", post.getText().toString());
                                i.putExtra("post",post);

                                flag = 1;
                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast
                                        .LENGTH_LONG).show();
                            }

                        }
                    });

        }

    }



    public void processing2() {
        {
            Intent insign = new Intent(this,
                    verification.class);
          // insign.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            insign.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            insign.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           // onPause();
            startActivity(insign);
           //onResume();
            final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                    "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {

                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG)
                                        .show();

                                Intent i = new Intent(signup.this, MainActivity.class);


                                i.putExtra("name", name.getText().toString());
                                i.putExtra("email", email.getText().toString());
                                i.putExtra("phone", phone.getText().toString());
                                //i.putExtra("post", post.getText().toString());
                                i.putExtra("post",post);

                                flag = 1;
                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast
                                        .LENGTH_LONG).show();
                            }

                        }
                    });


        }
    }*/

