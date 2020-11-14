package com.example.worldskillsbank;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JSONAdapter extends BaseAdapter {

    // создаем переменные
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Bankomat> objects;

    // создаем конструктор
    JSONAdapter(Context context, ArrayList<Bankomat> bankomats) {
        ctx = context;
        objects = bankomats;
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
            view = lInflater.inflate(R.layout.list_item2, parent, false);
        }

        // создаем переменную с позицией элемента
        Bankomat p = getBankomats(position);

        // выводим на экран содержимое объектов
        ((TextView) view.findViewById(R.id.textView13)).setText(p.getAddress());
        ((TextView) view.findViewById(R.id.textView14)).setText(p.getType());
        ((TextView) view.findViewById(R.id.textView16)).setText(p.getMon());

        // вывод статуса работы объекта
        try {
            if(getWork(p.getMon())){

                ((TextView) view.findViewById(R.id.textView15)).setText("Работает");
                ((TextView) view.findViewById(R.id.textView15)).setTextColor(Color.GREEN);
            }else{

                ((TextView) view.findViewById(R.id.textView15)).setText("Не работает");
                ((TextView) view.findViewById(R.id.textView15)).setTextColor(Color.RED);
            }
        }catch (Exception e){

        }

        // возвращаем шаблон
        return view;
    }

    // функция для проверки статуса работы объекта
    boolean getWork(String str) throws ParseException {
        int a = Integer.parseInt(str.substring(0,2));
        int b = Integer.parseInt(str.substring(8,10));
        int c = Integer.parseInt(str.substring(3,5));
        int d = Integer.parseInt(str.substring(12,14));
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh");
        DateFormat df1 = new SimpleDateFormat("mm");
        String s = df.format(currentTime);
        String s1 = df1.format(currentTime);
        int now = Integer.parseInt(s);
        int now1 = Integer.parseInt(s1);
        if( a <= now && now <= b){
            if (c <= now && now <=d ){
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }

    Bankomat getBankomats(int position) {
        return ((Bankomat) getItem(position));
    }
}
