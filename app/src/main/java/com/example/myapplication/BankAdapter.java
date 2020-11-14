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

    TextView tvIsWorking;
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BankTerminals> objects;

    BankAdapter(Context context, ArrayList<BankTerminals> banks) {
        ctx = context;
        objects = banks;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            view = lInflater.inflate(R.layout.list_view_modify_item, viewGroup, false);
        }

        BankTerminals p = getBanks(i);

        // заполняем View в пункте списка данными из отделения: адресс, работает ли, часы работы
        ((TextView) view.findViewById(R.id.tvAdress)).setText(p.getBankStreet());
        ((TextView) view.findViewById(R.id.tvTimeWork)).setText(p.getWorkTime());
        if (p.getIsWork()){
            ((TextView) view.findViewById(R.id.tvIsWorking)).setText("Работает");
            ((TextView) view.findViewById(R.id.tvIsWorking)).setTextColor(Color.parseColor("#FF03DAC5"));

        }else {
            ((TextView) view.findViewById(R.id.tvIsWorking)).setText("Не работает");
            ((TextView) view.findViewById(R.id.tvIsWorking)).setTextColor(Color.parseColor("#FFB00020"));
        }


        return view;
    }
    BankTerminals getBanks(int position) {
        return ((BankTerminals) getItem(position));
    }
}
