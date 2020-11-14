package com.example.mobileBank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class BankActivity extends AppCompatActivity {

    ListView listViewBank;

    //список банков
    ArrayList<Bank> banks;

    //адаптер для заполнения ListView списком банков banks
    BankAdapter bankAdapter;

    //!!!Тестирование адаптера, запросы и парсинг еще не реализоавны!!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        listViewBank = findViewById(R.id.listViewAtms);

        banks = new ArrayList<Bank>();
        bankAdapter = new BankAdapter(this, R.layout.list_item_currency, banks);

        ListView listView;
        listView = findViewById(R.id.listViewAtms);
        ArrayList<Bank> arrayList = new ArrayList<>();

        arrayList.add(new Bank( "Москва", "Банкомат", "15:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "09:00-20:00" ));
        arrayList.add(new Bank( "Москва", "Отделение","00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "09:00-10:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение",  "15:40-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "09:00-20:00" ));

        BankAdapter bankAdapter = new BankAdapter(this, R.layout.list_item_bank, arrayList);

        listView.setAdapter(bankAdapter);

    }

    //метод нажатия на кнопку "Главное меню"
    public void returnBack_Click(View view) {
        finish();
    }
}