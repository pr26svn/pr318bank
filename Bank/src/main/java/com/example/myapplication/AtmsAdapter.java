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

public class AtmsAdapter extends ArrayAdapter<AtmsBuildings> {
    private Context mContext;
    private int mResourse;


    public AtmsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<AtmsBuildings> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourse = resource;
    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);

            convertView = layoutInflater.inflate(mResourse, parent, false);

            TextView txtAddress = convertView.findViewById(R.id.txtAddress);

            TextView txtName = convertView.findViewById(R.id.txtName);

            TextView txtState = convertView.findViewById(R.id.txtState);

            TextView txtTime = convertView.findViewById(R.id.txtTime);

            txtAddress.setText(getItem(position).getAddress());

            txtName.setText(getItem(position).getName());

            txtState.setText(getItem(position).getState());

            txtTime.setText(getItem(position).getTime());

            return convertView;

        }
}
