package com.example.jobportalapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class JobAppliedAdapter extends ArrayAdapter<Apply> {
    private Activity context;
    private List<Apply> jobList;

    public JobAppliedAdapter(Activity context, List<Apply> jobList) {
        super(context, R.layout.jobapplyitem, jobList);
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.jobapplyitem,null,true);

        Apply Apply = jobList.get(position);

        TextView jobId = view.findViewById(R.id.job_Id);
        TextView jobTitle = view.findViewById(R.id.job_Title);
        TextView companyName = view.findViewById(R.id.company_Name);
        TextView jobStatus = view.findViewById(R.id.job_status);


        jobId.setText(Apply.getId());
        jobTitle.setText(Apply.getTitle());
        companyName.setText(Apply.getCom());
        jobStatus.setText(Apply.getStatus());



        return view;
    }
}
