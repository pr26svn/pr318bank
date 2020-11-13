package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Exchange_rates extends AppCompatActivity {

    public void  return_click(View view){
        Intent intent = new Intent(Exchange_rates.this,MainActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rates);



        ListView listView = findViewById(R.id.list_view2);
        ArrayList<Money> arrayList = new ArrayList<>();

        XmlPullParser xpp = getResources().getXml(R.xml.xml_daily);
        ParserTest parser = new ParserTest();
        if(parser.parse(xpp))
        {
            for(Money money: parser.getItems()){
                arrayList.add(money);
            }
        }

        adapter_money adapter_money = new adapter_money(this,R.layout.list2,arrayList);
        listView.setAdapter(adapter_money);
    }
}