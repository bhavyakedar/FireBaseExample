package com.hfad.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFireBaseAuth;
    EditText email,password;
    Button createAccount;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        mFireBaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mFireBaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            Toast.makeText(MainActivity.this,"You are already signed in.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        createAccount = findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()) {
                    email.setError("Please enter an email address here.");
                    email.requestFocus();
                }
                if(password.getText().toString().isEmpty()) {
                    password.setError("Please enter a password here.");
                    password.requestFocus();
                }
                if(!(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()))
                {
                    mFireBaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Please Sign-In.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"The entered Gmail address does not exist or it has already been registered.",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
