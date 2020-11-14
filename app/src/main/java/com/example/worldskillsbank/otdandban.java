package com.example.worldskillsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class otdandban extends AppCompatActivity {

    // создаем переменные
    private ArrayList<Bankomat> bankomats;
    public otdandban(){
        bankomats = new ArrayList<>();
    }
    ListView lv2;
    JSONAdapter adapter;
    Bankomat currentBankomat;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // соединяем код с дизайном
        setContentView(R.layout.activity_otdandban);

        // соединяем компоненты с переменными
        lv2 = (ListView) findViewById(R.id.listView1);


        AsyncTask.execute(new Runnable() {
            @Override public void run() {
                try {

                    // получаем JSON-файл
                    URL url = new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=Черкассы");
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    InputStream responseBody = connection.getInputStream();
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                    // создаем JSON-объект
                    Object obj = new JSONParser().parse(responseBodyReader);
                    JSONObject jo = (JSONObject) obj;

                    // создаем JSON-массив и передаем в него массив devices
                    JSONArray devicesArr = (JSONArray) jo.get("devices");
                    Iterator devicesItr;
                    devicesItr = devicesArr.iterator();

                    // ищем нужное и заполняем объекты
                    while (devicesItr.hasNext()) {
                        JSONObject test = (JSONObject) devicesItr.next();
                        JSONObject tw = (JSONObject) test.get("tw");
                        currentBankomat.setMon((String) tw.get("mon"));
                        currentBankomat.setAddress((String) jo.get("fullAddressRu"));
                        currentBankomat.setPlaceRu((String) jo.get("placeRu"));
                        bankomats.add(currentBankomat);
                    }
                    start();
                } catch (Exception e){
                    Log.d("Error", e.getMessage());
                }
            }
        });
    }

    // заполняем ListView
    private void start(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new JSONAdapter(otdandban.this, bankomats);
                lv2.setAdapter(adapter);
            }
        });
    }

    // выход на главную страницу
    public void onClick_button3(View view){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}