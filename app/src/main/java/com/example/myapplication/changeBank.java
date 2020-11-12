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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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
        //-----------------блок добавления отделений и банкоматов-----------------//

        //-----------------блок добавления отделений и банкоматов-----------------//

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, banksArray);
        listView = (ListView) findViewById(R.id.listView_1);
        listView.setAdapter(adapter);
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
}