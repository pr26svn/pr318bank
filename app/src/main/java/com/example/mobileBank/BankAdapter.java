package com.example.mobileBank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BankAdapter extends ArrayAdapter<Bank> {
    //позволит создавать объекты View для каждого элемента списка
    private LayoutInflater inflater;
    //ресурс разметки интерфейса для создания одного элемента ListView
    private int layout;
    //набор элементов, выводимых в ListView
    private List<Bank> banks;

    //конструктор BanksAdapter, получает ресурс разметки и набор объектов,
    //сохраняет их в отдельные переменные
    public BankAdapter(Context context, int resource, List<Bank> banks) {
        super(context, resource, banks);

        this.banks = banks;
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
        TextView address = (TextView) view.findViewById(R.id.txtAddress);
        TextView type = (TextView) view.findViewById(R.id.txtType);
        TextView working = (TextView) view.findViewById(R.id.txtWorking);
        TextView workingHours = (TextView) view.findViewById(R.id.txtWorkingHours);

        //получение элемента, для которого создается разметка
        Bank bank = banks.get(position);

        //заполнение элементов из объекта Currency
        address.setText(bank.getAddress());
        type.setText(bank.getType());
        working.setText(bank.getWorking());
        workingHours.setText(bank.getWorkingHours());

        return view;
    }
}

