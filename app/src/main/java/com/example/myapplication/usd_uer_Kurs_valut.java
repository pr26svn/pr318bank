package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class usd_uer_Kurs_valut extends AppCompatActivity {

    private Document doc;
    private Thread secThread;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usd_uer__kurs_valut);
        init();
    }
    public void inMain(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void init(){
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb(){
        try {
            doc = Jsoup.connect("http://minfin.com.ua/currency/").get();
            Log.d("MyLog","Title : " + doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}