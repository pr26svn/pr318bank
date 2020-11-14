package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomAdapterBank extends BaseAdapter {//создаем новый тип адаптера чтобы кастомизировать список
    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Bank> objects;

    CustomAdapterBank(Context context, ArrayList<Bank> banks){//констркутор
        ctx = context;
        objects = banks;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {//наследуется

        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }//наследуется

    @Override
    public long getItemId(int position) {
        return position;
    }//наследуется

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//наследуется
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.custom_item_banks, parent, false);
        }
        Bank b = getBank(position);//берем переданный объект

        ((TextView) view.findViewById(R.id.bank_adress)).setText(b.getAddress());//устанавливаем адресс
        ((TextView) view.findViewById(R.id.bank_time)).setText(b.getTw());//устанавливаем время работы
        ((TextView) view.findViewById(R.id.bank_type)).setText(b.getType());//устанавливаем тип (отделение/банковский модуль)
        try {
            if(getWork(b.getTw())){//для установки текста об информации о работе

                ((TextView) view.findViewById(R.id.bank_work)).setText("Работает");
                ((TextView) view.findViewById(R.id.bank_work)).setTextColor(Color.GREEN);
            }else{

                ((TextView) view.findViewById(R.id.bank_work)).setText("Не работает");
                ((TextView) view.findViewById(R.id.bank_work)).setTextColor(Color.RED);
            }
        }catch (Exception e){

        }

        return view;
    }

    boolean getWork(String str) throws ParseException {//возвращает true- если работает, false-если не работает(часы работы)
        int a = Integer.parseInt(str.substring(0,2));//внимание костыль
        int b = Integer.parseInt(str.substring(8,10));
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh");
        String s = df.format(currentTime);
        int now = Integer.parseInt(s);

        if( a < now && now < b){
            return true;
        }else{
            return false;
        }
    }
    Bank getBank(int position){
        return ((Bank) getItem(position));
    }
}
