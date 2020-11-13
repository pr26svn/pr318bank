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

        /*AsyncTask.execute(new Runnable() {
            @Override public void run() {
                try{
                    URL url = new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city");

                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    connection.setRequestProperty("Contact-Me", "hathibelagal@example.com");
                    InputStream responseBody = connection.getInputStream();
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        Bank bank = new Bank();
                        String key = jsonReader.nextName();
                        if (key.equals("type")) {
                            bank.setType(jsonReader.nextString());
                        } else if (key.equals("mon")) {
                            bank.setWorkingHours(jsonReader.nextString());
                        } else if (key.equals("fullAddressRu")){
                            bank.setAddress(jsonReader.nextString());
                        } else {
                            jsonReader.skipValue();
                        }
                        banks.add(bank);
                    }
                    jsonReader.close();
                    connection.disconnect();
                } catch (Exception e){
                    Log.e("Error", "Error");
                }
            }
        });/*

        /*ListView listView;
        listView = findViewById(R.id.listViewAtms);
        ArrayList<Bank> arrayList = new ArrayList<>();

        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Закрыт", "09:00-20:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Закрыт", "09:00-20:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Банкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Работает", "00:00-00:00" ));
        arrayList.add(new Bank( "Москва", "Отделение", "Закрыт", "09:00-20:00" ));

        BankAdapter bankAdapter = new BankAdapter(this, R.layout.list_item_bank, arrayList);

        listView.setAdapter(bankAdapter);*/

    }

    /*private void parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {

        Object obj = null;
        try {
            obj = new JSONParser().parse(file_for_parsing);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;

        org.json.simple.JSONArray devices=(org.json.simple.JSONArray) jo.get("devices");


        Iterator devices_itr = devices.iterator();


        while (devices_itr.hasNext()) {
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();


            JSONObject tw = (JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            timetable = tw.get("mon").toString();

            Bank banks = new Bank(adress_obj.get("fullAddressRu").toString(), timetable);
            bankArrayList.add(banks);

            list.add(banks.getAllData());
        }
    }*/

    //метод нажатия на кнопку "Главное меню"
    public void returnBack_Click(View view) {
        finish();
    }
}