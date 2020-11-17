package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ValutAdapter extends BaseAdapter{
    //переменные
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ValutResourse> objects;
    //адпатер
    ValutAdapter(Context context, ArrayList<ValutResourse> otdelenies) {
        ctx = context;
        objects = otdelenies;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //базовые методы и их заполнение
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
            view = lInflater.inflate(R.layout.item_valut, viewGroup, false);
        }

        ValutResourse p = getValute(i);

        // заполняем View в пункте списка данными из отделения: адресс, работает-не работает, часы работы
        ((TextView) view.findViewById(R.id.code)).setText(p.getCharCode());
        ((TextView) view.findViewById(R.id.name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.values)).setText(p.getValue());
        return view;
    }
    ValutResourse getValute(int position) {
        return ((ValutResourse) getItem(position));
    }
}
