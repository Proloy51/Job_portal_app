package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminJobPostList extends AppCompatActivity {
    String username;
    private FloatingActionButton fab;
    ListView listView;
    private List<Job> joblist;
    DatabaseReference databaseReference;
    private CustomAdapter customAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_job_post_list);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }
        fab=findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminJobPostList.this,PaymentActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });


        listView = findViewById(R.id.listViewId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Company Jobs").child(username);
        joblist = new ArrayList<>();
        customAdapter = new CustomAdapter(AdminJobPostList.this,joblist);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Job job = dataSnapshot.getValue(Job.class);
                    joblist.add(job);
                }
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminJobPostList.this,JobRecruiterHomePage.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}


