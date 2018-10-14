package com.example.jerry.odapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LogsActivity extends AppCompatActivity {

    private Toolbar lToolBar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private FirebaseUser user;
    String emailName;
    private ArrayList<String> names=new ArrayList<>();
    private ArrayList<String> reasons=new ArrayList<>();
    private ArrayList<String> regno=new ArrayList<>();
    private ArrayList<String> sections=new ArrayList<>();
    private ArrayList<String> status=new ArrayList<>();
    private ArrayList<String> dates=new ArrayList<>();
    private RecyclerView rview;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        lToolBar=(Toolbar)findViewById(R.id.logs_action_bar);
        setSupportActionBar(lToolBar);
        getSupportActionBar().setTitle("Logs");
        mAuth=FirebaseAuth.getInstance();
        rview = findViewById(R.id.rview);
        user=mAuth.getCurrentUser();
        emailName = user.getEmail();
        emailName = emailName.substring(0, emailName.indexOf('@'));

        mDataBase=FirebaseDatabase.getInstance().getReference().child(emailName);
        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ApplicationHandler applicationHandler=dataSnapshot.getValue(ApplicationHandler.class);
                names.add(applicationHandler.getName());
                reasons.add(applicationHandler.getReason());
                regno.add(applicationHandler.getRegNo());
                sections.add(applicationHandler.getSec());
                dates.add(applicationHandler.getTo());
                status.add(applicationHandler.getStatus());
                adapter = new HandlerOdForm(LogsActivity.this, names, regno, sections, reasons, dates,status);
                manager = new LinearLayoutManager(LogsActivity.this, LinearLayoutManager.VERTICAL, false);
                rview.setLayoutManager(manager);
                rview.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.logs_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.logs_logout){
            FirebaseAuth.getInstance().signOut();
            updateUI();
        }
        return true;
    }
    public void updateUI() {
        Intent intent = new Intent(LogsActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
