package com.example.worldskillsbank;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class XMLAdapter extends BaseAdapter {

    // создаем переменные
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Currency> objects;

    // создаем конструктор
    XMLAdapter(Context context, ArrayList<Currency> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // получаем количество элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // получаем элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // получаем id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // заполняем шаблон
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        // получаем шаблон
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }

        // создаем переменную с позицией элемента
        Currency p = getCurrency(position);

        // выводим на экран содержимое объектов
        ((TextView) view.findViewById(R.id.textView9)).setText(p.getLittleName());
        ((TextView) view.findViewById(R.id.textView10)).setText(p.getName());
        ((TextView) view.findViewById(R.id.textView11)).setText(p.getPrice());
        ((TextView) view.findViewById(R.id.textView12)).setText(p.getCell());

        // возвращаем шаблон
        return view;
    }

    Currency getCurrency(int position) {
        return ((Currency) getItem(position));
    }
}
