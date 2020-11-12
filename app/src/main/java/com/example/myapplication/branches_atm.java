package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;

public class branches_atm extends AppCompatActivity {
    ListView listView;

    ArrayAdapter<String> arrayAdapter;

    ArrayList<String> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches_atm);
        listView = findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }

    public void onMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private String downloandData () {
        String query = "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";
        HttpsURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            connection = (HttpsURLConnection) new URL(query).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                }
                System.out.println(sb.toString());
            } else {
                System.out.println("fail" + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return sb.toString();
        }
    }

    private void parsingFile(String file_for_parsing) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException
    {
            Object obj = null;
            try {
                obj = new JSONParser().parse(file_for_parsing);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject jo = (JSONObject) obj;


            JSONArray devices = (JSONArray) jo.get("devices");


            Iterator devices_itr = devices.iterator;


            while (devices_itr.hasNext()) {
                JSONObject adress_obj = (JSONObject) devices_itr.next();


                JSONObject tw = (JSONObject) adress_obj.get("tw");
                String timetable = "00-00";
                timetable = tw.get("mon").toString();

                Banks banks = new Banks(adress_obj.get("fullAddressRu").toString(), timetable);

                arrayList.add(banks.returnStreet()+"\t"+banks.returntimeWork());
            }
        }
    }
}