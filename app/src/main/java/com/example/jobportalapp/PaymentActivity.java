package com.example.jobportalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String username;
    Button button;
    String[] paymentMethods = {"Bkash","Rocket","Nagad","Visa Card","Master Card"};
    int[] paymentIcons = {R.drawable.bkash_logo,R.drawable.rocket_logo, R.drawable.nagad_logo,
            R.drawable.visa_card_logo,R.drawable.mastercard_logo};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            username = bundle.getString("username");
        }
        button = findViewById(R.id.button);
        Spinner paymentSpinner = findViewById(R.id.spinner);
        paymentSpinner.setOnItemSelectedListener(this);
        SpinnerAdapter adapter = new SpinnerAdapter(this,paymentMethods,paymentIcons);
        paymentSpinner.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this,admin_job_post.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}