package com.example.clone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class mainCurrency extends AppCompatActivity {

    ListView listView;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_currency);

        Date date;

        TextView mainDate;

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.textViewOfDate);
        mainDate.setText(dateText);

        listView = (ListView) findViewById(R.id.listCurrency);


       /* ArrayList<Currency> currencyArrayList = new ArrayList<>();
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        currencyArrayList.add(new Currency(R.drawable.usa, "Американский доллар", "USD", "77,19"));
        */



        String blockXml = "";
        XmlPullParser xpp = getResources().getXml(R.xml.currancy);
        CurrencyParser currencyParser = new CurrencyParser();
        currencyParser.parse(xpp);
        PersonAdapter currencyAdapter = new PersonAdapter(this, R.layout.list_main, currencyParser.currencies);

        listView.setAdapter(currencyAdapter);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}