package com.example.e_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {

    Button loginAsMemberButton;
    Button loginAsAdminButton;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        loginAsMemberButton = findViewById(R.id.loginButtonMember);
        loginAsAdminButton= findViewById(R.id.loginButtonAdmin);


        loginAsMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        loginAsAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( WelcomeActivity.this,LoginadminActivity.class);
                startActivity(intent);
            }
        });


    }
}

