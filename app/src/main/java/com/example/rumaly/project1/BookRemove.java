package com.example.rumaly.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hosneara on 11/16/16.
 */
public class BookRemove extends AppCompatActivity{
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookremove);

        editText = (EditText)findViewById(R.id.edit);
        button = (Button)findViewById(R.id.delete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBook book = DeleteBook(); //model
                AdminView adminView = new AdminView(); //view
                AdminController adminController = new AdminController(book, adminView); //controller
                adminController.deleteBook();
                Toast.makeText(BookRemove.this, book.getName()+" has been removed successfully",
                        Toast
                        .LENGTH_SHORT).show();
            }
        });

    }

    private newBook DeleteBook()
    {
        newBook book = new newBook();
        book.setName(editText.getText().toString());
        return book;
    }
}
