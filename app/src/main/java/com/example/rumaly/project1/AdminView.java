package com.example.rumaly.project1;

import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by hosneara on 11/16/16.
 */

public class AdminView {
    private Firebase Bookfirebase, BookDetailFirebase, BookDeleteFirebabse;
    private Map newbook;
    private String bookname, author, edition, type, key;
    private String bookPath, bookDetailsPath, bookdeletePath;

    AdminView()
    {
    }

    public void setBookEnvironvent(String bookname, String author, String edition, String type)
    {
        this.bookname = bookname;
        this.author = author;
        this.edition = edition;
        this.type = type;

        bookPath = "https://project1-4257e.firebaseio.com/BooksDatabase/"+type;
        bookDetailsPath = bookPath + "/" +bookname;
    }
    public void setBookEnvForDelete()
    {
        bookdeletePath = "https://project1-4257e.firebaseio.com/BooksDatabase/";
    }
    public void BookEntry()
    {
        Bookfirebase = new Firebase(bookPath);
        BookDetailFirebase = new Firebase(bookDetailsPath);

        newbook = new HashMap();
        newbook.put(bookname," ");
        Bookfirebase.updateChildren(newbook);

        newbook = new HashMap();
        newbook.put("writer",author);
        BookDetailFirebase.updateChildren(newbook);

        newbook = new HashMap();
        newbook.put("edition",edition);
        BookDetailFirebase.updateChildren(newbook);

        newbook = new HashMap();
        newbook.put("TotalBooks","total 10");
        BookDetailFirebase.updateChildren(newbook);
    }

    public void BookDelete(final String bookname)
    {
        final Firebase firebase = new Firebase(bookdeletePath);

        firebase.orderByChild(bookname).addListenerForSingleValueEvent(new com
                .firebase.client.ValueEventListener(){
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for(com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren())
                {
                    Log.d("bookshot",bookshot.getKey());
                    if(bookshot.hasChild(bookname) == true)
                    {
                        key = bookshot.getKey();
                        Log.d("Found key",bookshot.getKey());
                        bookdeletePath += key + "/"+bookname;
                        break;
                    }
                }
                Log.d("mykey",key);
                Log.d("bookdeletepath",bookdeletePath);
                BookDeleteFirebabse = new Firebase(bookdeletePath);
                BookDeleteFirebabse.removeValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                Log.d("User",firebaseError.getMessage() );
            }
        });
    }
}
