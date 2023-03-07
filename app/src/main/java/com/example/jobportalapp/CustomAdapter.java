package com.example.jobportalapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Job> {
    private Activity context;
    private List<Job> jobList;

    public CustomAdapter(Activity context, List<Job> jobList) {
        super(context, R.layout.item, jobList);
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item,null,true);

        Job Job = jobList.get(position);

        TextView jobId = view.findViewById(R.id.job_Id);
        TextView jobTitle = view.findViewById(R.id.job_Title);
        TextView companyName = view.findViewById(R.id.company_Name);
        TextView salary = view.findViewById(R.id.salary_amount);
        TextView desc = view.findViewById(R.id.job_Desc);
        TextView req = view.findViewById(R.id.job_Req);
        TextView date = view.findViewById(R.id.post_Date);

        jobId.setText(Job.getId());
        jobTitle.setText(Job.getTitle());
        companyName.setText(Job.getCom());
        salary.setText(Job.getSal());
        desc.setText(Job.getDesc());
        req.setText(Job.getReq());
        date.setText(Job.getDate());


        return view;
    }
}
