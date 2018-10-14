package com.example.jerry.odapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private Button submit;
    private EditText name, regNo, reason, from, to, classAdvisor;
    private Spinner department, year, section;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String emailName;
    String getdepartment, getYear, getSection, getName, getRegNo, getReason, getFrom, getTo, getClassAdvisor,status="Pending";
    private DatabaseReference mFirebaseDatabase;
    private Toolbar hToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        department = (Spinner) findViewById(R.id.Dept);
        year = (Spinner) findViewById(R.id.year);
        section = (Spinner) findViewById(R.id.Sec);
        submit = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.Name);
        regNo = (EditText) findViewById(R.id.RegisterNo);
        reason = (EditText) findViewById(R.id.Reason);
        from = (EditText) findViewById(R.id.From);
        to = (EditText) findViewById(R.id.To);
        classAdvisor = (EditText) findViewById(R.id.ClassAdviosr);
        hToolbar = (Toolbar) findViewById(R.id.home_actionbar);
        setSupportActionBar(hToolbar);
        getSupportActionBar().setTitle("OD Form");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        emailName = user.getEmail();
        emailName = emailName.substring(0, emailName.indexOf('@'));
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
                Toast.makeText(HomeActivity.this, currentDateandTime, Toast.LENGTH_SHORT).show();
                getdepartment = String.valueOf(department.getSelectedItem().toString().trim());
                getYear = String.valueOf(year.getSelectedItem().toString().trim());
                getSection = String.valueOf(section.getSelectedItem().toString().trim());
                getClassAdvisor = classAdvisor.getText().toString().trim();
                getName = name.getText().toString().trim();
                getRegNo = regNo.getText().toString().trim();
                getRegNo = getRegNo.toUpperCase();
                getReason = reason.getText().toString().trim();
                getFrom = from.getText().toString().trim();
                getTo = to.getText().toString().trim();
                if (TextUtils.isEmpty(getName) || TextUtils.isEmpty(getRegNo) || TextUtils.isEmpty(getReason) || TextUtils.isEmpty(getFrom) || TextUtils.isEmpty(getTo) || TextUtils.isEmpty(getClassAdvisor)) {
                    Toast.makeText(HomeActivity.this, "Enter details in the missing field", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApplicationHandler applicationHandler = new ApplicationHandler(getName, getRegNo, getdepartment, getSection, getYear, getReason, getFrom, getTo, getClassAdvisor, emailName,status);
                String email = getClassAdvisor.substring(0, getClassAdvisor.indexOf('@')).trim();
                Toast.makeText(HomeActivity.this, email, Toast.LENGTH_LONG).show();
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child(email);
                String key=mFirebaseDatabase.push().getKey();
                mFirebaseDatabase.child(key).setValue(applicationHandler);
                Pending pending=new Pending(getName,getReason,getRegNo,getSection,getTo,emailName,status);
                mFirebaseDatabase=FirebaseDatabase.getInstance().getReference().child(emailName);
                mFirebaseDatabase.child(key).setValue(applicationHandler);
                startActivity(new Intent(HomeActivity.this,LogsActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            updateUI();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.main_logout) {
            FirebaseAuth.getInstance().signOut();
            updateUI();
        }
        if (item.getItemId() == R.id.main_logs) {
            startActivity(new Intent(HomeActivity.this, LogsActivity.class));
        }
        return true;
    }

    public void updateUI() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
