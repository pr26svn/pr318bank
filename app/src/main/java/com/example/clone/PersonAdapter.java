package com.example.clone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonAdapter extends ArrayAdapter<Currency> {

    private Context mContext;
    private int mResources;
    public PersonAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Currency> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResources = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResources, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView txtCharCode = convertView.findViewById(R.id.txtCharCode);
        TextView txtValue= convertView.findViewById(R.id.txtValue);
        TextView txtFullName = convertView.findViewById(R.id.txtFullName);

        imageView.setImageResource(getItem(position).getImage());
        txtCharCode.setText(getItem(position).getCharCode());
        txtValue.setText(getItem(position).getValue());
        txtFullName.setText(getItem(position).getName());
        return convertView;
    }
}
