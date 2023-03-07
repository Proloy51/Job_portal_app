package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class JobProviderLogin extends AppCompatActivity {
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    int count = 0;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_provider_login);
        Button button1 = findViewById(R.id.btn_login);
        Button button2 = findViewById(R.id.btn_signup);
        EditText usertxt = findViewById(R.id.username_login);
        EditText emailtxt = findViewById(R.id.email_login);
        EditText passwordtxt = findViewById(R.id.password_login);
        progressBar = findViewById(R.id.progressBarId);
        progressBar.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }

            private void userLogin() {
                String user = usertxt.getText().toString().trim();
                String email = emailtxt.getText().toString().trim();
                String password = passwordtxt.getText().toString().trim();
                if(user.isEmpty()){
                    usertxt.setError("Enter a username");
                    usertxt.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    emailtxt.setError("Enter an email address");
                    emailtxt.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailtxt.setError("Enter a valid email address");
                    emailtxt.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordtxt.setError("Enter an email address");
                    passwordtxt.requestFocus();
                    return;
                }
                if(password.length()<6){
                    passwordtxt.setError("Minimum length of a password should be 6");
                    passwordtxt.requestFocus();
                    return;
                }

                databaseReference.child("JobProvider").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(user)) {
                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        Timer timer = new Timer();
                                        TimerTask timerTask = new TimerTask() {
                                            @Override
                                            public void run() {
                                                count++;
                                                progressBar.setProgress(count);
                                                if(count == 30){
                                                    timer.cancel();
//                                                    Toast.makeText(JobSeekerLogin.this, "Login is successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(JobProviderLogin.this, JobRecruiterHomePage.class);
                                                    intent.putExtra("username",user);
                                                    startActivity(intent);
                                                }
                                            }
                                        };
                                        timer.schedule(timerTask,0,30);
                                    }
                                    else {
                                        emailtxt.setError("Wrong Email");
                                        emailtxt.requestFocus();
                                        passwordtxt.setError("Or Wrong Password");
                                        passwordtxt.requestFocus();
                                    }
                                }
                            });

                        }
                        else{
                            usertxt.setError("Username doesn't exist");
                            usertxt.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobProviderLogin.this,JobProviderSignup.class);
                startActivity(intent);
            }
        });
    }
}