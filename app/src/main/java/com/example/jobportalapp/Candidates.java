package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Candidates extends AppCompatActivity{
    String username;
    static ArrayList<String>usernames = new ArrayList<>(100);
    ListView listView;
    private List<Apply> joblist;
    DatabaseReference databaseReference;
    private CustomAdapterCandidates customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        listView = findViewById(R.id.ListViewId);

        databaseReference = FirebaseDatabase.getInstance().getReference("Applied Jobs");

        joblist = new ArrayList<>();
        customAdapter = new CustomAdapterCandidates(Candidates.this, joblist);
        databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    joblist.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                       usernames.add(dataSnapshot.getKey()) ;

                    }
                    for (int i = 0;i<usernames.size();i++) {
                        String user = usernames.get(i);
                        databaseReference.child(user).child(username).addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Apply apply = dataSnapshot.getValue(Apply.class);
                                    joblist.add(apply);
                                }
                                listView.setAdapter(customAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    usernames.clear();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
        });

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                joblist.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                {
//                    Apply apply = dataSnapshot.getValue(Apply.class);
//                    joblist.add(apply);
//                }
//                listView.setAdapter(customAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error){
//
//            }
//        });
    }

}
