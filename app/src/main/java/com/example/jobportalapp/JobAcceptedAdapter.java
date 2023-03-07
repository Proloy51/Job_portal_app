package com.example.jobportalapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class JobAcceptedAdapter extends ArrayAdapter<Accepted> {
    private Activity context;
    private List<Accepted> jobList;

    public JobAcceptedAdapter(Activity context, List<Accepted> jobList) {
        super(context, R.layout.jobaccepteditem, jobList);
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.jobaccepteditem,null,true);

        Accepted Accepted = jobList.get(position);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView jobId = view.findViewById(R.id.acpt_job_Id);
        TextView jobTitle = view.findViewById(R.id.acpt_job_Title);
        TextView companyName = view.findViewById(R.id.acpt_company_Name);
        TextView jobStatus = view.findViewById(R.id.acpt_job_status);


        jobId.setText(Accepted.getId());
        jobTitle.setText(Accepted.getTitle());
        companyName.setText(Accepted.getCom());
        jobStatus.setText(Accepted.getStat());

        return view;
    }
}
