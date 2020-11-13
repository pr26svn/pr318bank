package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class secondActivity extends AppCompatActivity {
    Date date;
    TextView mainDate;
    TextView txtXml;

    ListView listView;
    CurrencyAdapter currencyAdapter;
    ArrayList<Currency> currencyArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(date);
        mainDate=(TextView) findViewById(R.id.textView13);
        mainDate.setText(dateText);

        listView=(ListView)findViewById(R.id.listView1);
/*        String blokXml="";
        txtXml = (TextView) findViewById(R.id.textView13);
        XmlPullParser xpp = getResources().getXml(R.xml.currency);
        currencyParser parser = new currencyParser();
        if (parser.parse(xpp))
        {
            for (Currency currencyParser: parser.getCurrencies()){
                blokXml = blokXml + currencyParser.toString() + "\n";
            }
            txtXml.setText(blokXml);
        }*/
    }



    public void finish(View view) {
        finish();
    }
}
