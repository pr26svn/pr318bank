package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Currency extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    TextView dateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        dateView = (TextView) findViewById(R.id.tvDate);
        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        dateView.setText(dateText);

        listView = findViewById(R.id.list);

        ArrayList<CurrencyClass> arrayList = new ArrayList<>();

        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));
        arrayList.add(new CurrencyClass(R.drawable.usaflag, "USD",
                "Американский доллар", "65", "65"));


        CurrencyAdapter currencyAdapter = new CurrencyAdapter(this,
                R.layout.list_row_currency, arrayList);

        listView.setAdapter(currencyAdapter);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}