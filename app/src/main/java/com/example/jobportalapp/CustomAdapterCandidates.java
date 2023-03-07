package com.example.jobportalapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapterCandidates extends ArrayAdapter<Apply> {

    private Activity context;
    private List<Apply> jobList;
    DatabaseReference ref,ref2,refAccept,refReject;
    public CustomAdapterCandidates(Activity context, List<Apply> jobList) {
        super(context, R.layout.appliedcandidate, jobList);
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.appliedcandidate,null,true);

        Apply Apply = jobList.get(position);

        ref = FirebaseDatabase.getInstance().getReference().child("Applied Jobs");
        ref2 = FirebaseDatabase.getInstance().getReference().child("User Wise Job Apply");
        refAccept = FirebaseDatabase.getInstance().getReference().child("Accepted");
        refReject = FirebaseDatabase.getInstance().getReference().child("Rejected");

        TextView username = view.findViewById(R.id.user_name);
        TextView jobId = view.findViewById(R.id.Id_job);
        TextView jobTitle = view.findViewById(R.id.job_Title);
        TextView candidName = view.findViewById(R.id.name_candidate);
        TextView company = view.findViewById(R.id.company_to_apply);
        TextView hsc = view.findViewById(R.id.hsc_perc);
        TextView grad = view.findViewById(R.id.grad_perc);
        TextView postGrad = view.findViewById(R.id.post_grad_perc);
        TextView workExp = view.findViewById(R.id.work_exp);
        TextView spec = view.findViewById(R.id.spec);
        TextView skill = view.findViewById(R.id.skill);
        Button accpt = view.findViewById(R.id.accept_btn);
        Button reject = view.findViewById(R.id.reject_btn);


        username.setText(Apply.getUser());
        String user = username.getText().toString();
        jobId.setText(Apply.getId());
        String jobid = jobId.getText().toString();
        jobTitle.setText(Apply.getTitle());
        String jobtitle = jobTitle.getText().toString();
        candidName.setText(Apply.getCandid());
        String candidate = candidName.getText().toString();
        company.setText(Apply.getCom());
        String comp = company.getText().toString();
        hsc.setText(Apply.getHsc());
        grad.setText(Apply.getGrad());
        postGrad.setText(Apply.getPostgrad());
        workExp.setText(Apply.getExp());
        spec.setText(Apply.getSpec());
        skill.setText(Apply.getSkill());
        String acpt_status = "Accepted";
        String rjct_status = "Rejected";

        accpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Accepted", Toast.LENGTH_SHORT).show();
                Accepted accepted = new Accepted(jobid,jobtitle,comp,acpt_status);
                refAccept.child(user).child(jobid).setValue(accepted);
                ref.child(user).child(comp).child(jobid).removeValue();
                ref2.child(user).child(jobid).removeValue();

            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Rejected", Toast.LENGTH_SHORT).show();
                Rejected rejected = new Rejected(jobid,jobtitle,comp,rjct_status);
                refReject.child(user).child(jobid).setValue(rejected);
                ref.child(user).child(comp).child(jobid).removeValue();
                ref2.child(user).child(jobid).removeValue();

            }
        });

        return view;
    }

}
