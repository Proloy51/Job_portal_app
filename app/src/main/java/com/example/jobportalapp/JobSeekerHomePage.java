package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JobSeekerHomePage extends AppCompatActivity {
    String username;
    private CardView allJobs,appliedJobs,acceptedJobs,rejectedJobs,profile,logout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_home_page);

        allJobs = findViewById(R.id.jobsCardViewId);
        appliedJobs = findViewById(R.id.appliedJobsCardViewId);
        acceptedJobs =  findViewById(R.id.acceptedJobsCardViewId);
        rejectedJobs = findViewById(R.id.rejectedJobsCardViewId);
        profile = findViewById(R.id.profileCardViewId);
        logout = findViewById(R.id.logoutCardViewId);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        allJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerHomePage.this,AllJobsPost.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        appliedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerHomePage.this,AppliedJobs.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        acceptedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerHomePage.this,JobAcceptedActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        rejectedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerHomePage.this,JobRejectActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerHomePage.this,JobSeekerProfile.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(JobSeekerHomePage.this)
                        .setMessage("Do you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(JobSeekerHomePage.this,MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Do you want to logout?")
                .setCancelable(false)
                .setPositiveButton("logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(JobSeekerHomePage.this,MainActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("NO",null)
                .show();

    }
}