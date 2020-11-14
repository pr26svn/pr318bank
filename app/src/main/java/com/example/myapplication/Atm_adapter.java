package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Atm_adapter extends ArrayAdapter<Atm> {

    private Context mContext;
    private int mResources;
    public Atm_adapter(@NonNull Context context, int resource, @NonNull ArrayList<Atm> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResources = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResources, parent, false);

        TextView txtStreet = convertView.findViewById(R.id.textView16);
        TextView txtStatus= convertView.findViewById(R.id.textView18);
        TextView txtView = convertView.findViewById(R.id.textView17);
        TextView txtWork = convertView.findViewById(R.id.textView20);

        txtStreet.setText(getItem(position).getStreet());
        txtStatus.setText(getItem(position).getStatus());
        txtView.setText(getItem(position).getView());
        txtWork.setText(getItem(position).getTime());
        return convertView;
    }
}
