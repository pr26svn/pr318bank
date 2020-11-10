package com.example.mobilebank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Otdelenie> objects;

    BoxAdapter(Context context, ArrayList<Otdelenie> otdelenies) {
        ctx = context;
        objects = otdelenies;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        Otdelenie p = getOtdelenie(position);

        // заполняем View в пункте списка данными из отделения: адресс, работает ли, часы работы
        ((TextView) view.findViewById(R.id.tvAdress)).setText(p.Adress);
        ((TextView) view.findViewById(R.id.tvIsWorking)).setText(p.Is_working);
        ((TextView) view.findViewById(R.id.tvTimeWork)).setText("Часы работы " + p.Time_work);
        ((TextView) view.findViewById(R.id.tvType)).setText(p.Type);

        return view;
    }

    // товар по позиции
    Otdelenie getOtdelenie(int position) {
        return ((Otdelenie) getItem(position));
    }
}
