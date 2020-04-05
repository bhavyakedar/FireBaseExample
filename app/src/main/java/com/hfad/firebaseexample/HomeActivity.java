package com.hfad.firebaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button logout;
    FirebaseAuth mFireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mFireBaseAuth = FirebaseAuth.getInstance();
               mFireBaseAuth.signOut();
               Intent intent = new Intent(HomeActivity.this,MainActivity.class);
               startActivity(intent);
               finish();
            }
        });
    }
}
