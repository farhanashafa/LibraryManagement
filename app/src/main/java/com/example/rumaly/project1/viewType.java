package com.example.rumaly.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by USER on 11/11/2016.
 */
public class viewType extends AppCompatActivity {

    private String[] catList = {"FirstYear", "SecondYear", "ThirdYear", "FourthYear", "MS", "Others"};

    private Button categoty;
    private Button alphabetical;
    private ListView list;
    private ArrayAdapter<String> array;
    private String post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtype);

        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                Log.d("LogInSuccess ", "UserID not found");
            }
            else {
                post =extras.getString("userPost"); //changed
                Log.d("GotPost", post);
            }
        }


        categoty = (Button) findViewById(R.id.Category);
        alphabetical = (Button) findViewById(R.id.alphabetical);

        array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, catList);

        categoty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, catList);
                list = (ListView) findViewById(R.id.categoryList);
                list.setAdapter(array);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(viewType.this, BookList.class);
                        i.putExtra("category", catList[position]);
                        i.putExtra("userPost",post);
                        startActivity(i);
                    }
                });

            }
        });


        alphabetical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(viewType.this, BookList.class);
                i.putExtra("category", "All");
                i.putExtra("userPost",post);
                startActivity(i);
            }
        });
    }
}


/*RelativeLayout.LayoutParams head1Params = new RelativeLayout.LayoutParams(
                        AbsoluteLayout.LayoutParams.FILL_PARENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT);
                head1Params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                alphabetical.setLayoutParams(head1Params);
                /*Intent i = new Intent(viewType.this, BookList.class);
                startActivity(i);*/