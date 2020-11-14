package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Dengi;
import com.example.myapplication.R;

import java.util.ArrayList;

public class adapter_Dengi extends ArrayAdapter<Dengi> {

    private Context context;
    private int resource;


    public adapter_Dengi(@NonNull Context context, int resource, @NonNull ArrayList<Dengi> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
    }

    //Поиск нужных элементов TextView и заполнение их информацией
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView=layoutInflater.inflate(resource,parent,false);

        TextView currency = convertView.findViewById(R.id.textView13);

        TextView abbreviation = convertView.findViewById(R.id.textView11);

        TextView buy = convertView.findViewById(R.id.textView19);

        TextView sell = convertView.findViewById(R.id.textView18);


        currency.setText(getItem(position).getCurrency());

        abbreviation.setText(getItem(position).getAbbreviation());

        buy.setText(getItem(position).getBuy());

        sell.setText(getItem(position).getBuy());

        return convertView;
    }
}
