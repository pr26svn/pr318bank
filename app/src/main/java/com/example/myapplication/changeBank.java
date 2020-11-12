package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.CredentialProtectedWhileLockedViolation;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

public class changeBank extends AppCompatActivity {

    private Button buttonBack;
    public ArrayList<String> banksArray;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_bank);

        buttonBack = (Button) findViewById(R.id.button_4);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(changeBank.this, MainActivity.class);
                startActivity(intent);
            }
        });

        banksArray = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, banksArray);
        listView = (ListView) findViewById(R.id.listView_1);
        listView.setAdapter(adapter);

        try {
            parsingJSONFiles(downloadJSONFile());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    private void parsingJSONFiles(String filePath) throws ParserConfigurationException, IOException, SAXException, ParseException, JSONException {
        Object obj = null;
        try {
            obj = new JSONParser().parse(filePath);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        org.json.simple.JSONObject jo = (org.json.simple.JSONObject) obj;

        org.json.simple.JSONArray devices = (org.json.simple.JSONArray) jo.get("devices");

        Iterator devices_itr = devices.iterator();

        while (devices_itr.hasNext()) {
            org.json.simple.JSONObject adress_obj = (org.json.simple.JSONObject) devices_itr.next();

            JSONObject tw = (JSONObject) adress_obj.get("tw");
            String timetable = "00-00";
            try {
                timetable = tw.get("mon").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            BankTerminals bt = new BankTerminals(adress_obj.get("fullAddressRu").toString(), timetable);
            banksArray.add(bt.getBankData());
        }
    }
}