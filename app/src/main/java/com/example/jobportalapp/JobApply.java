package com.example.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobApply extends AppCompatActivity {
    String username;
   private Button jobApply;
   private EditText user,jobId,jobTitle,jobCandidate,company,Hsc,underGrad,postGrad,workExp,spec,skill;
   DatabaseReference databaseReference,ref;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_apply);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        ref = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }

        jobApply = findViewById(R.id.jobApply);
        user = findViewById(R.id.usernameText);
        jobId = findViewById(R.id.jobIdText);
        jobTitle = findViewById(R.id.jobtitleText);
        jobCandidate = findViewById(R.id.candidateTitleEditText);
        company = findViewById(R.id.companyToApplyText);
        Hsc = findViewById(R.id.HSCEditText);
        underGrad = findViewById(R.id.underGradEditText);
        postGrad = findViewById(R.id.postGradEditText);
        workExp = findViewById(R.id.experienceEditText);
        spec = findViewById(R.id.specializationEditText);
        skill = findViewById(R.id.skillEditText);

        jobApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });

    }

    private void savedata() {
        String User = user.getText().toString();
        String job_id = jobId.getText().toString();
        String job_title = jobTitle.getText().toString();
        String job_candidate = jobCandidate.getText().toString();
        String comp = company.getText().toString();
        String HSC = Hsc.getText().toString();
        String under_grad = underGrad.getText().toString();
        String post_grad = postGrad.getText().toString();
        String work_exp = workExp.getText().toString();
        String SPEC = spec.getText().toString();
        String SKILL = skill.getText().toString();
        String status = "Pending";

        if(User.isEmpty()){
            user.setError("Empty Field");
            user.requestFocus();
        }
        else if(job_id.isEmpty()){
            jobId.setError("Empty Field");
            jobId.requestFocus();
        }
        else if(job_title.isEmpty()){
            jobTitle.setError("Empty Field");
            jobTitle.requestFocus();
        }
        else if(job_candidate.isEmpty()){
            jobCandidate.setError("Empty Field");
            jobCandidate.requestFocus();
        }
        else if(comp.isEmpty()){
            company.setError("Empty Field");
            company.requestFocus();
        }
        else if(HSC.isEmpty()){
            Hsc.setError("Empty Field");
            Hsc.requestFocus();
        }
        else if(under_grad.isEmpty()){
            underGrad.setError("Empty Field");
            underGrad.requestFocus();
        }
        else if(post_grad.isEmpty()){
            postGrad.setError("Empty Field");
            postGrad.requestFocus();
        }
        else if(work_exp.isEmpty()){
            workExp.setError("Empty Field");
            workExp.requestFocus();
        }
        else if(SPEC.isEmpty()){
            spec.setError("Empty Field");
            spec.requestFocus();
        }
        else if(SKILL.isEmpty()){
            skill.setError("Empty Field");
            skill.requestFocus();
        }
        else if(!User.equals(username)){
            user.setError("User Id Doesn't Match");
            user.requestFocus();
        }
        else {
            Apply apply = new Apply(User, job_id, job_title, job_candidate, comp, HSC, under_grad, post_grad, work_exp, SPEC, SKILL, status);
            databaseReference.child("Applied Jobs").child(username).child(comp).child(job_id).setValue(apply);
            ref.child("User Wise Job Apply").child(username).child(job_id).setValue(apply);

            Toast.makeText(JobApply.this, "Job Applied", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(JobApply.this, AllJobsPost.class);
            intent.putExtra("username", username);
            startActivity(intent);

        }

    }
}