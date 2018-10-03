package com.example.jerry.odapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/***
 Made by Jerry,Nawaz and Abhishek.
 the login page
 */

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login, signup, forgot;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    //to get the email and password in string format.
    String getEmail, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.Semail);
        password = (EditText) findViewById(R.id.Spassword);
        login = (Button) findViewById(R.id.Slogin);
        signup = (Button) findViewById(R.id.signup);
        forgot = (Button) findViewById(R.id.forgot);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail = email.getText().toString().trim();
                getPassword = password.getText().toString().trim();
                if (TextUtils.isEmpty(getEmail)) {
                    email.setError(getString(R.string.no_email));
                    return;
                }
                if (TextUtils.isEmpty(getPassword)) {
                    if (getPassword.length() < 6) {
                        password.setError(getString(R.string.pass_length));
                    } else {
                        password.setError(getString(R.string.no_password));
                    }
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                if (getEmail.charAt(0) == '_') {
                    mAuth.signInWithEmailAndPassword(getEmail, getPassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                       // Intent intent = new Intent(LoginActivity.this, TeacherHomeActivity.class);
                                       // startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else {
                    mAuth.signInWithEmailAndPassword(getEmail, getPassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user!=null){
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
