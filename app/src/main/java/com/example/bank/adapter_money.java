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

public class adapter_money extends ArrayAdapter<Money> {

    private Context context;
    private int resource;


    public adapter_money(@NonNull Context context, int resource, @NonNull ArrayList<Money> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView=layoutInflater.inflate(resource,parent,false);

        TextView currency = convertView.findViewById(R.id.textView13);

        TextView abbreviation = convertView.findViewById(R.id.textView11);

        TextView buy = convertView.findViewById(R.id.textView19);

        TextView sell = convertView.findViewById(R.id.textView18);

        //TextView USD = convertView.findViewById(R.id.textView6);

        //TextView EUR = convertView.findViewById(R.id.textView7);

        currency.setText(getItem(position).getCurrency());

        abbreviation.setText(getItem(position).getAbbreviation());

        buy.setText(getItem(position).getBuy());

        sell.setText(getItem(position).getBuy());

       // USD.setText(getItem(position).getDollars());

       // EUR.setText(getItem(position).getEuros());

        return convertView;
    }
}
