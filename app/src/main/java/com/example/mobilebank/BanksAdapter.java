package com.example.mobilebank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BanksAdapter extends ArrayAdapter<Banks> {

    private Context mContext;
    private int mResourse;


    public BanksAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Banks> objects) {
        super(context, resource, objects);

        this.mContext=context;
        this.mResourse=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView=layoutInflater.inflate(mResourse,parent,false);

        TextView streets = convertView.findViewById(R.id.streets);

        TextView atms = convertView.findViewById(R.id.atms);

        TextView jobs = convertView.findViewById(R.id.jobs);

        TextView hours = convertView.findViewById(R.id.hours);

        streets.setText(getItem(position).getStreets());

        atms.setText(getItem(position).getAtm());

        jobs.setText(getItem(position).getJobs());

        hours.setText(getItem(position).getHours());


        return convertView;
    }
}
