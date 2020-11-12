package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;


import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class Bank extends AppCompatActivity {

    TextView textView;
    final String TAG = "myTag";
    String text;
    ListView listView;
    ArrayList<String>list=new ArrayList<String >();
    ArrayList<Banks>bankArrayList=new ArrayList<Banks>();
    ArrayAdapter<Banks>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        Button button3 = (Button) findViewById(R.id.button8);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bank.this, MainActivity.class);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        textView=(TextView)findViewById(R.id.textView2);
        adapter = new ArrayAdapter<Banks>(this, android.R.layout.simple_list_item_1, bankArrayList);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                   text= downloandData();
                   textView.setText(text);
                } finally {

                }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    parsing(text);
                                    listView.setAdapter(adapter);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                });
                }


    private String downloandData(){
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

                Log.d(TAG, sb.toString());
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
            return sb.toString();
        }
    }

    private void parsing(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {

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

            Banks banks = new Banks(adress_obj.get("fullAddressRu").toString(), timetable);
            bankArrayList.add(banks);

            list.add(banks.getAllData());
        }
    }



}