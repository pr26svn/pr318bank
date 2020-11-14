package com.example.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ValuteAdapter extends BaseAdapter {

    TextView tvIsWorking;
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Valutes> objects;

    ValuteAdapter(Context context, ArrayList<Valutes> banks) {
        ctx = context;
        objects = banks;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            //меняю layout на свой собственный
            view = lInflater.inflate(R.layout.item_valute, viewGroup, false);
        }

        Valutes p = getValute(i);

        // заполняем View в пункте списка данными из отделения: адресс, работает ли, часы работы
        ((TextView) view.findViewById(R.id.name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.charCode)).setText(p.getValueCode());
        ((TextView) view.findViewById(R.id.value)).setText(p.getValue());
        return view;
    }
    Valutes getValute(int position) {
        return ((Valutes) getItem(position));
    }
}
