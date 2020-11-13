package com.example.mobilebank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.mobilebank.MainActivity.money_format;

public class CurrencyAdapter extends BaseAdapter {
    /**
     * Этот адаптер для ListView в котором будут отображаться валюты
     */


    // Объявляют нужные мне переменные
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Valuta> objects;


    CurrencyAdapter(Context context, ArrayList<Valuta> vlt) {
        ctx = context;
        objects = vlt;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {

            // меняю layout  на свой собственный
            view = lInflater.inflate(R.layout.currency_item, parent, false);
        }

        Valuta p = getValuta(position);



        // заполняем View в пункте списка данными из валюты: название, полное название, цена купли/продажи
        ((TextView) view.findViewById(R.id.tvName)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvFullName)).setText(p.full_name);
        String formattedDouble1 = new DecimalFormat(money_format).format(p.buy);
        ((TextView) view.findViewById(R.id.tvBuy)).setText(String.valueOf(formattedDouble1));
        ((TextView) view.findViewById(R.id.tvSell)).setText(String.valueOf(p.sell));

        return view;
    }

    // валюта по позиции
    Valuta getValuta(int position) {
        return ((Valuta) getItem(position));
    }

}
