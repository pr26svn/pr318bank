package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterValute extends BaseAdapter {
    //переменные
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Valutes> objects;

    //создание своего адаптера
    adapterValute(Context context, ArrayList<Valutes> otdelenies) {
        ctx = context;
        objects = otdelenies;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //заполнение методов базового адаптера
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
            view = lInflater.inflate(R.layout.item, viewGroup, false);
        }

        Valutes p = getValute(i);

        // заполняю view данными
        ((TextView) view.findViewById(R.id.charCode)).setText(p.getCharCode());
        ((TextView) view.findViewById(R.id.name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.value)).setText(p.getValue());
        return view;
    }
    Valutes getValute(int position) {
        return ((Valutes) getItem(position));
    }
}