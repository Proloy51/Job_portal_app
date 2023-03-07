package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class JobRecruiterHomePage extends AppCompatActivity {
    String username;
    private CardView jobs,candidates,profile,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_recruiter_home_page);
        jobs = findViewById(R.id.jobsCardViewId);
        candidates = findViewById(R.id.candidatesCardViewId);
        profile = findViewById(R.id.profileCardViewId);
        logout = findViewById(R.id.logoutCardViewId);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(JobRecruiterHomePage.this,AdminJobPostList.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        candidates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobRecruiterHomePage.this,Candidates.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobRecruiterHomePage.this,JobProviderProfile.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(JobRecruiterHomePage.this)
                        .setMessage("Do you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(JobRecruiterHomePage.this,MainActivity.class));
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
                        startActivity(new Intent(JobRecruiterHomePage.this,MainActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("NO",null)
                .show();

    }
}