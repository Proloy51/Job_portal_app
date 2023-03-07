package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppliedJobs extends AppCompatActivity {
    String username;
    ListView listView;
    private List<Apply> joblist;
    DatabaseReference databaseReference;
    private JobAppliedAdapter customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_jobs);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        listView = findViewById(R.id.ListViewId);
        databaseReference = FirebaseDatabase.getInstance().getReference("User Wise Job Apply").child(username);
        joblist = new ArrayList<>();
        customAdapter = new JobAppliedAdapter(AppliedJobs.this,joblist);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                joblist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Apply apply = dataSnapshot.getValue(Apply.class);
                    joblist.add(apply);
                }
                listView.setEmptyView(findViewById(android.R.id.empty));
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });

    }
}