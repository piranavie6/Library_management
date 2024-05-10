package com.example.e_library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagebooksActivity extends AppCompatActivity {

    private Button button;
    private DBManager dbManager;

    private EditText editText1, editText2, editText3, editText4, editText5;
    private Button addbook, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);

        button = findViewById(R.id.btnback3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();

        addbook = findViewById(R.id.btnbookadd);
        clear = findViewById(R.id.btndelete);
        editText1 = findViewById(R.id.txtBookID);
        editText2 = findViewById(R.id.txtBooktitle);
        editText3 = findViewById(R.id.txtpublishername);
        editText4 = findViewById(R.id.txtauthorname2);
        editText5 = findViewById(R.id.txtaddbranchname);

        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertBook();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    private boolean validateFields() {
        if (isEmpty(editText1) || isEmpty(editText2) || isEmpty(editText3) || isEmpty(editText4) || isEmpty(editText5)) {
            Toast.makeText(getApplicationContext(), "Fields can't be null", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void insertBook() {
        try {
            String bookID = editText1.getText().toString();
            String bookName = editText2.getText().toString();
            String bookPublisher = editText3.getText().toString();
            String bookAuthor = editText4.getText().toString();
            String branch = editText5.getText().toString();

            dbManager.insert("insert into Book values('" + bookID + "','" + bookName + "','" +
                    bookPublisher + "','" + bookAuthor + "','" + branch + "')");
            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
            Log.e("first", "Inserted");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in Book Adding", Toast.LENGTH_SHORT).show();
            Log.e("error", "Error in Book Adding: " + e.getMessage());
        }
    }

    private void clearFields() {
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        editText4.getText().clear();
        editText5.getText().clear();
        Toast.makeText(getApplicationContext(), "Your Successfully Deleted", Toast.LENGTH_SHORT).show();
    }

    public void adminMenu() {
        Intent intent = new Intent(this, AdminmenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
