package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Iterator;


import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class Banks_and_ATMs extends AppCompatActivity {

    public void return_click(View view){
        Intent intent = new Intent(Banks_and_ATMs.this, MainActivity.class);
        startActivity(intent);
    }

    String text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_and__a_t_ms);

        ListView listView = findViewById(R.id.list_view);
        ArrayList<atms_and_banks>arrayList = new ArrayList<>();

        text = downloadInfo();
        Parse(text);


        adapter_bank_atm adapter = new adapter_bank_atm(this, R.layout.list, arrayList);

        listView.setAdapter(adapter);
    }

    private void Parse (String file) {

        try {
            Object object = new JSONParser().parse(file);
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) object;


            org.json.simple.JSONArray devices = (org.json.simple.JSONArray)jsonObject.get("devices");


            Iterator iterator = devices.iterator();

            while (iterator.hasNext()){
                org.json.simple.JSONObject address = (org.json.simple.JSONObject) iterator;

                JSONObject work_time = (JSONObject) address.get("tw");
                String time = "00:00";
                time = work_time.get("mon").toString();

                atms_and_banks atms_and_banks = new atms_and_banks(address.get("fullAddressRu").toString(),time);

            }
        }
        catch (Exception cause){
            cause.printStackTrace();
        }
    }

    private String downloadInfo(){
        String query= "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";
        HttpsURLConnection connection = null;
        StringBuilder sb= new StringBuilder();
        try{
            connection = (HttpsURLConnection)new URL(query).openConnection();

            connection.setRequestMethod("GET");

            connection.connect();

            if(HttpsURLConnection.HTTP_OK==connection.getResponseCode()){

                BufferedReader in= new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                String line;
                while((line=in.readLine())!=null){
                    sb.append(line);
                    sb.append('\n');
                }
                System.out.println(sb.toString());

            } else {
                System.out.println("fail"+connection.getResponseCode()+ ", "+ connection.getResponseMessage());
            }
        }
        catch (Throwable cause){
            cause.printStackTrace();
        } finally {
            if(connection!=null){
                connection.disconnect();
            }
        }
        return sb.toString();
    }
}