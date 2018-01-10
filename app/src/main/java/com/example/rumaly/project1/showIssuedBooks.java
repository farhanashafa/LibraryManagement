package com.example.rumaly.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by USER on 11/15/2016.
 */
public class showIssuedBooks extends AppCompatActivity {
    private ListView lv;
    private FirebaseUser user;
    private List books = new ArrayList();
    private ArrayAdapter<String> arrayadapter;
    private EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_issued_books);

        lv = (ListView) findViewById(R.id.issuedbooklist);
        inputSearch = (EditText) findViewById(R.id.inputSearchIssuedBooks);

        user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid().toString();

        Firebase firebase = new Firebase("https://project1-4257e.firebaseio.com/BOOKS");

        firebase.addListenerForSingleValueEvent(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                for (com.firebase.client.DataSnapshot bookshot : dataSnapshot.getChildren()) {
                    String str = bookshot.getKey().toString();
                    if (uid.equals(str)) {
                        Map<String, String> map = bookshot.getValue(Map.class);
                        Log.d("MyIssuedBooks ", bookshot.getValue().toString());
                        getValues(map);
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //Toast.makeText(BookList.this, "Error in data retrieval", Toast.LENGTH_SHORT).show();
            }
        });
        arrayadapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.Search, books);
        lv.setAdapter(arrayadapter);
        lv.setVisibility(View.VISIBLE);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                showIssuedBooks.this.arrayadapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    }
    private void getValues(Map<String, String> map){
        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            String str[] = map.get(key).split(",");
            books.add(str[0]);
            Log.d("IssuedBook ", key);
            Log.d("IssuedBook ", str[0]);
        }
    }
}

        /*for(int i = 0; i <= 1000; i++)
            Log.d("Entering ", "succeded");
        long lastSec = System.currentTimeMillis();
        while(true){
            long sec = System.currentTimeMillis();
            if ( sec -lastSec > 3000) {
                break;
            }
        }*/