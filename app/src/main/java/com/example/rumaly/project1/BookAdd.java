package com.example.rumaly.project1;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hosneara on 11/3/16.
 */


public class BookAdd {
    private FirebaseUser user;
    private Firebase mrootref;
    private int counter = 0;
    private String submit;
    private String bookname;
   // private static BookAdd bookAdd = new BookAdd();
    BookAdd()
    {
        user = FirebaseAuth.getInstance().getCurrentUser();
        mrootref = new Firebase(
                "https://project1-4257e.firebaseio.com/BOOKS/"+user.getUid());

    }
    public void setBook(String bookname, int position, String submit)
    {
        Map newBook = new HashMap();
        newBook.put("Book "+position,bookname+","+submit);
        mrootref.updateChildren(newBook);

    }
}
