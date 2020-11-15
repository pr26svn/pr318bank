package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class Bank extends AppCompatActivity {
    BankAdapter adapter;
    TextView textView;
    String text;
    ListView listView;
    ArrayList<Banks> bankArrayList = new ArrayList<Banks>();
    final String TAG="";
    @Override

    //Представляет собой первоначальную настройку activity,в частности, создаются объекты визуального интерфейса
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        listView = (ListView) findViewById(R.id.listView1);
        adapter = new BankAdapter(this, bankArrayList);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run(){
                String query = "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";

                HttpsURLConnection connection = null;
                final StringBuilder sb = new StringBuilder();
                try {
                    connection = (HttpsURLConnection) new URL(query).openConnection();

                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    connection.connect();

                    if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                        Log.d(TAG, "-1");

                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while ((line = in.readLine()) != null) {
                            sb.append(line);

                            System.out.println(line);
                        }
                        in.close();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("0");
                                parsing(sb.toString());
                                listView.setAdapter(adapter);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        System.out.println("1");

        Object obj = null;
        try {
            obj = new JSONParser().parse(file_for_parsing);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;

        org.json.simple.JSONArray devices = (org.json.simple.JSONArray) jo.get("devices");


        Iterator devices_itr = devices.iterator();
        System.out.println("2");


        while (devices_itr.hasNext()) {
            System.out.println("3");
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();


            org.json.simple.JSONObject tw = (org.json.simple.JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            timetable = tw.get("mon").toString();

            Banks banks = new Banks(adress_obj.get("fullAddressRu").toString(), timetable);
            Log.d(TAG, "Успешно");
            bankArrayList.add(banks);
        }
    }

    //Метод для перехода на главное меню,по стрелке в activity_bank
    public void onMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    //Обработчик нажатия на кнопку,переносит на activity_bank
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
