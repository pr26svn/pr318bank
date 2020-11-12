package com.example.mobileBank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Адаптер, связывающий набор даннных из валют типа Currency
 * и объект ListView
 */
public class CurrencyAdapter extends ArrayAdapter<Currency> {
    //позволит создавать объекты View для каждого элемента списка
    private LayoutInflater inflater;
    //ресурс разметки интерфейса для создания одного элемента ListView
    private int layout;
    //набор элементов, выводимых в ListView
    private List<Currency> currencies;

    //конструктор CurrencyAdapter, получает ресурс разметки и набор объектов,
    //сохраняет их в отдельные переменные
    public CurrencyAdapter(Context context, int resource, List<Currency> currencies) {
        super(context, resource, currencies);

        this.currencies = currencies;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    //метод, в котором устанавливается отображение элемента списка
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //создание объекта View для каждого отдельного элемента в списке
        View view=inflater.inflate(this.layout, parent, false);

        //получение объектов по id из созданного элемента View
        TextView name = (TextView) view.findViewById(R.id.txtCurrencyName);
        TextView fullName = (TextView) view.findViewById(R.id.txtFullCurrencyName);
        TextView buy = (TextView) view.findViewById(R.id.txtBuy);
        TextView sell = (TextView) view.findViewById(R.id.txtSell);

        //получение элемента, для которого создается разметка
        Currency currency = currencies.get(position);

        //заполнение элементов из объекта Currency
        name.setText(currency.getName());
        fullName.setText(currency.getFullName());
        buy.setText(currency.getBuy());
        sell.setText(currency.getSell());

        return view;
    }
}
