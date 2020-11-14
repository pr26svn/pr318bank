package com.example.clone;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ThirthActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_window);

        Date date;

        TextView mainDate;

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate = (TextView) findViewById(R.id.txtDate);
        mainDate.setText(dateText);

        String blockXml = "";
        //listView = (ListView) findViewById(R.id.listCurrancy);
        //        XmlPullParser xpp = getResources().getXml(R.xml.currancy);
        //        CurrencyParser currencyParser = new CurrencyParser();
        //        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(this, R.layout.currency_window,
        //                currencyParser.currencies);
        //
        //        if (currencyParser.parse(xpp)) {
        //
        //        }

    }
}