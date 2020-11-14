package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class bankomat extends AppCompatActivity {

    Intent intent;
    Bank tempBank;
    ArrayList <Bank> banks = new ArrayList<Bank>();
    CustomAdapterBank adapter;

    ListView list_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankomat);
        list_bank = findViewById(R.id.listBank);
        run_Process();
    }
    public void onClick_Home(View view){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    private void funk(){//выделяем дополнительный поток для обновления графики ибо так хотел андроид
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new CustomAdapterBank(bankomat.this, banks);
                list_bank.setAdapter(adapter);
            }
        });
    }
    private void run_Process(){
        AsyncTask.execute(new Runnable() {
            @Override public void run() {
                try{
                    URL url = new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=Черкассы");//чтобы не считывать абсолютно все банки и отделения взял город Черкассы
                    HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
                    InputStream responseBody = myConnection.getInputStream();//подключаемся
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                    Object object = new JSONParser().parse(responseBodyReader);
                    JSONObject jsonObject = (JSONObject) object;//читаем объект
                    JSONArray jsonArray = (JSONArray) jsonObject.get("devices");//читаем массив

                    Iterator deviceItr = jsonArray.iterator();

                    while(deviceItr.hasNext()){//идем по массиву пока можем
                        JSONObject test = (JSONObject) deviceItr.next();
                        JSONObject tw = (JSONObject) test.get("tw");//доп обект т.к время работы выглядит так tw{mon:00:00-12:00...}
                        tempBank = new Bank();
                        String s = test.get("fullAddressRu").toString();//выводим адресс
                        s = s.substring(s.indexOf("город")).replace("город", "г.");//меняю длинное слово на короткое
                        tempBank.setAddress(s);
                        tempBank.setTw(tw.get("fri").toString());
                        tempBank.setType(test.get("placeRu").toString());
                        try {
                            banks.add(tempBank);
                        }catch (Exception e){

                        }
                    }
                    funk();
                } catch (Exception e){
                    Log.e("Error", e.getMessage());
                }
            }
        });

    }
}
