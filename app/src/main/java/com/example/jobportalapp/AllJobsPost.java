package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllJobsPost extends AppCompatActivity {
    String username;
    private SearchView searchView;
    ListView listView;
    private List<Job> joblist;
    DatabaseReference databaseReference;
    private CustomAdapterJobApply customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_jobs_post);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        listView = findViewById(R.id.ListViewId);
        databaseReference = FirebaseDatabase.getInstance().getReference("All Jobs");
        joblist = new ArrayList<>();
        customAdapter = new CustomAdapterJobApply(AllJobsPost.this,joblist);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                joblist.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Job job = dataSnapshot.getValue(Job.class);
                    joblist.add(job);
                }
                listView.setEmptyView(findViewById(android.R.id.empty));
                listView.setAdapter(customAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(AllJobsPost.this,JobApply.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }


        });


    }
    public void onBackPressed() {
        Intent intent = new Intent(AllJobsPost.this,JobSeekerHomePage.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}