package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Bank extends AppCompatActivity {

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

        ListView listView;
        listView = findViewById(R.id.listView);
        ArrayList<Banks> arrayList = new ArrayList<>();

        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "00:00-00:00" ));
        arrayList.add(new Banks( "Москва", "Бонкомат", "Работает", "09:00-20:00" ));

        BanksAdapter banksAdapter = new BanksAdapter(this, R.layout.list, arrayList);

        listView.setAdapter(banksAdapter);

    }

    private void downloandData (){
        String query= "https://api.privatbank.ua/p24api/infrastructure?json&atm&address=&city=";
        HttpsURLConnection connection = null;
        try{
            connection = (HttpsURLConnection)new URL(query).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection  .setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb= new StringBuilder();

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
    }
}