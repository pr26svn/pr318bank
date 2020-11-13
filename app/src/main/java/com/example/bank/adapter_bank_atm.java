package com.example.bank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class adapter_bank_atm extends ArrayAdapter<atms_and_banks> {


    private Context context;
    private int resource;


    public adapter_bank_atm(@NonNull Context context, int resource, @NonNull ArrayList<atms_and_banks> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView=layoutInflater.inflate(resource,parent,false);

        TextView street = convertView.findViewById(R.id.street);

        TextView bank_and_atm = convertView.findViewById(R.id.bank_and_atm);

        TextView is_working = convertView.findViewById(R.id.is_working);

        TextView work_hours = convertView.findViewById(R.id.work_hours);

        street.setText(getItem(position).getStreet());

        //bank_and_atm.setText(getItem(position).getBank_and_atm());

        //is_working.setText(getItem(position).getIs_working());

        work_hours.setText(getItem(position).getWork_hours());


        return convertView;
    }
}
