package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BankAdapter extends BaseAdapter {
    //переменные
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BankResourse> objects;

    //адаптер
    BankAdapter(Context context, ArrayList<BankResourse> otdelenies) {
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
            //меняем layout на свой собственный
            view = lInflater.inflate(R.layout.list_item_bank, viewGroup, false);
        }

        BankResourse p = getBanks(i);

        // заполняем View в пункте списка данными: адресс, работает-не работает, часы работы
        ((TextView) view.findViewById(R.id.adress)).setText(p.getStreet());
        ((TextView) view.findViewById(R.id.timeWork)).setText(p.getWorkTime());
        if (p.isworking){
            ((TextView) view.findViewById(R.id.working)).setText("Работает");
            ((TextView) view.findViewById(R.id.working)).setTextColor(Color.parseColor("#FF03DAC5"));

        }else {
            ((TextView) view.findViewById(R.id.working)).setText("Не работает");
            ((TextView) view.findViewById(R.id.working)).setTextColor(Color.parseColor("#FFB00020"));
        }


        return view;
    }
    BankResourse getBanks(int position) {
        return ((BankResourse) getItem(position));
    }
}
