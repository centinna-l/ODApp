package com.example.jerry.odapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText email,password;
    private Button login,register;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    String getEmail,getPassword;
    private Toolbar sToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=(EditText)findViewById(R.id.Semail);
        password=(EditText)findViewById(R.id.Spassword);
        login=(Button)findViewById(R.id.Slogin);
        register=(Button)findViewById(R.id.Sregister);
        progressBar=(ProgressBar)findViewById(R.id.SprogressBar);
        sToolbar=(Toolbar)findViewById(R.id.register_actionbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmail=email.getText().toString().trim();
                getPassword=password.getText().toString().trim();
                if(TextUtils.isEmpty(getEmail)){
                    email.setError(getString(R.string.no_email));
                    return;
                }
                if(TextUtils.isEmpty(getPassword)){
                    password.setError(getString(R.string.pass_length));
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(getEmail,getPassword)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent=new Intent(SignUp.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, "Registration error!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
