package com.example.mobilebank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ATMAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<a_t_m> objects;

    ATMAdapter(Context context, ArrayList<a_t_m> otdelenies) {
        ctx = context;
        objects = otdelenies;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    // банкоматов по позиции
    a_t_m getOtdelenie(int position) {
        return ((a_t_m) getItem(position));
    }
    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }
    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }
    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }
    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные данные
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.a_t_m_item, parent, false);
        }
        a_t_m p = getOtdelenie(position);
        // заполняем View в пункте списка данными из отделения
        ((TextView) view.findViewById(R.id.tvAdress)).setText(p.fullAddressRu);
        ((TextView) view.findViewById(R.id.tvTimeWork)).setText(p.TW);
        ((TextView) view.findViewById(R.id.tvIsWorking)).setText("Работает");
        return view;
    }
}
