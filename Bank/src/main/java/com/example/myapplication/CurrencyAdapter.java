package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
/*
    Класс адаптера валют, предназначенный для вывода значений валют на экран
 */

public class CurrencyAdapter extends ArrayAdapter<CurrencyClass> {
    private Context mContext;
    private int mResourse;

    // Конструктор адаптера
    public CurrencyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CurrencyClass> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourse = resource;
    }

    // Вывод на экран
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResourse, parent, false);

        ImageView flag = convertView.findViewById(R.id.imageViewFlag);

        TextView currency = convertView.findViewById(R.id.txtCurrency);

        TextView name = convertView.findViewById(R.id.txtName);

        TextView buy = convertView.findViewById(R.id.txtBuy);

        TextView sell = convertView.findViewById(R.id.txtSell);

        flag.setImageResource(getItem(position).getFlag());

        currency.setText(getItem(position).getCurrency());

        name.setText(getItem(position).getName());

        buy.setText(getItem(position).getBuy());

        sell.setText(getItem(position).getSell());



        return convertView;

    }
}
