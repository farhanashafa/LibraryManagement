package com.example.rumaly.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hosneara on 10/28/16.
 */

public class BookList extends Activity {

    private List bookname = new ArrayList();
    public List author = new ArrayList();
    private List total = new ArrayList();

    private ListView lv;
    private ArrayAdapter<String> arrayadapter;
    private EditText inputSearch;
    private String year = "";
    private int flag = 0;

    private String post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        lv = (ListView) findViewById(R.id.booklist);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Log.d("LogInSuccess ", "Post not found");
            } else {
                year = extras.getString("category");
                post = extras.getString("userPost"); //changed
                Log.d("Gotpost", post);
                if (year.equals("All")) {
                    strategy ob = new AllBooks(year);
                    bookname = ob.operation();
                    author = ob.getAuthor();
                    total = ob.getTotalBooksList();
                } else {
                    strategy ob = new myCategory(year);
                    bookname = ob.operation();
                    author = ob.getAuthor();
                    total = ob.getTotalBooksList();
                    Log.d("ViewList ", Integer.toString(bookname.size()));
                }
            }

            arrayadapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.Search, bookname);
            lv.setAdapter(arrayadapter);
            lv.setVisibility(View.VISIBLE);

            inputSearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    BookList.this.arrayadapter.getFilter().filter(cs);
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

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(BookList.this, BookDetails.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("book", (String) bookname.get(position));
                    i.putExtra("author", (String) author.get(position));
                    i.putExtra("totalBooks", (String) total.get(position));
                    i.putExtra("userPost",post);
                    startActivity(i);
                }
            });
        }
    }
}