package com.example.clone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BancomatsAdapter extends ArrayAdapter<MainBancomats> {

    private Context mContext;
    private int mResources;
    public BancomatsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MainBancomats> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResources = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResources, parent, false);

        TextView txtStreet = convertView.findViewById(R.id.textStreet);
        TextView txtStatus= convertView.findViewById(R.id.txtStatus);
        TextView txtView = convertView.findViewById(R.id.textBrachesOfBancomats);
        TextView txtTimeOfWork = convertView.findViewById(R.id.textTimeOfWork);

        txtStreet.setText(getItem(position).getStreet());
        txtStatus.setText(getItem(position).getStatus());
        txtView.setText(getItem(position).getView());
        txtTimeOfWork.setText(getItem(position).getTime());
        return convertView;
    }
}
