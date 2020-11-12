package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Activity_Bank extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__bank);
        Button button4 = (Button) findViewById(R.id.buttonB);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Bank.this,MainActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.banksList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        ArrayList<String>arrayList = new ArrayList<>();

    }
    public String downloadJSONFile() {
        HttpURLConnection connection = null;
        StringBuilder builder = null;
        try {
            connection = (HttpURLConnection) new URL("https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=").openConnection();

            connection.setRequestMethod("getJSONFile");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            builder = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return builder.toString();
    }
    private void Parse (String file) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {

        Object object = new JSONParser().parse(file);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) object;


        JSONArray devices = (JSONArray) jsonObject.get("devices");


        Iterator iterator = devices.iterator();

        while (iterator.hasNext()) {
            org.json.simple.JSONObject address = (org.json.simple.JSONObject) iterator;

            JSONObject work_time = (JSONObject) address.get("tw");
            String time = "00:00";
            time = work_time.get("mon").toString();
        }
    }
}