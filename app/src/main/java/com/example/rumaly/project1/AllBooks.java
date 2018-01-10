package com.example.rumaly.project1;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 11/15/2016.
 */
public class AllBooks implements strategy{
    private List temp = new ArrayList();
    private String[] catList = {"FirstYear", "SecondYear", "ThirdYear", "FourthYear", "MS", "Others"};
    private List bookname = new ArrayList();
    private List  author = new ArrayList();
    private String year = "";
    private List totalBooks = new ArrayList();

    AllBooks(String S){
        year = S;
    }
    public List operation(){
        int i = 0;
        for( ; i < catList.length; i++ ){
            year = "";
            year = catList[i];
            Log.v("Want: ", year);
            Firebase firebase = new Firebase("https://project1-4257e.firebaseio.com/BooksDatabase/" + year);
            firebase.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
                @Override
                public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                    for (com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren()) {
                        //String str = bookshot.getKey().toString();
                        bookname.add(bookshot.getKey().toString());
                        Map<String, String> map = bookshot.getValue(Map.class);
                        author.add(map.get("writer"));
                        Log.d("bookname: ", bookshot.getKey().toString());
                        String[] str = map.get("TotalBooks").split(" ");
                        totalBooks.add(str[1]);
                        Log.d("author: ", str[1]);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    //Toast.makeText(BookList.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return bookname;
    }

    @Override
    public List getAuthor() {
        return author;
    }
    public List getTotalBooksList(){ return totalBooks; }
}
