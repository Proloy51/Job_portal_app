package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobSeekerProfile extends AppCompatActivity {
    int count = 0;
    String fName,lName,email,userId,username;
    TextView first,last,mail,user,HI;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_profile);
        first = findViewById(R.id.firstName);
        last = findViewById(R.id.lastName);
        mail = findViewById(R.id.userMail);
        user = findViewById(R.id.userName);
        HI = findViewById(R.id.myName);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("JobSeeker").child(username);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    if(count==0){
                        email = dataSnapshot.getValue().toString();
                        mail.setText(email);
                    }
                    if(count == 1){
                        fName = dataSnapshot.getValue().toString();
                        first.setText(fName);
                        HI.setText(fName);
                    }
                    if(count == 2){
                        lName = dataSnapshot.getValue().toString();
                        last.setText(lName);
                    }
                    if(count == 4){
                        userId = dataSnapshot.getValue().toString();
                        user.setText(userId);
                    }
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}