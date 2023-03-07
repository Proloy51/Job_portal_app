package com.example.jobportalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    String[] paymentMethods;
    int[] paymentIcons;

    public SpinnerAdapter(Context context, String[] paymentMethods, int[] paymentIcons) {
        this.context = context;
        this.paymentMethods = paymentMethods;
        this.paymentIcons = paymentIcons;
    }

    @Override
    public int getCount() {
        return paymentMethods.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.payment_items,parent,false);
        ImageView imageView = view.findViewById(R.id.imageIcon);
        TextView textView = view.findViewById(R.id.tv);

        imageView.setImageResource(paymentIcons[position]);
        textView.setText(paymentMethods[position]);

        return view;
    }
}
