package com.example.mobilebank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BanksAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Banks> objects;


    BanksAdapter(Context context, ArrayList<Banks> otdelenies) {
        ctx = context;
        objects = otdelenies;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = lInflater.inflate(R.layout.list_row, viewGroup, false);
        }

        Banks p = getBanks(i);


        // заполняем View в пункте списка данными из отделения: адресс, работает ли, часы работы
        ((TextView) view.findViewById(R.id.streets)).setText(p.getStreet());
        ((TextView) view.findViewById(R.id.hours)).setText(p.getWorkTime());
        if (p.Working) {
            ((TextView) view.findViewById(R.id.jobs)).setText("Работает");
            ((TextView) view.findViewById(R.id.jobs)).setTextColor(Color.parseColor("#FF03DAC5"));

        } else {
            ((TextView) view.findViewById(R.id.jobs)).setText("Не работает");
            ((TextView) view.findViewById(R.id.jobs)).setTextColor(Color.parseColor("#FFB00020"));
        }


        return view;
    }

    Banks getBanks(int position) {
        return ((Banks) getItem(position));
    }
}



    /*private Context mContext;
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
    }*/

