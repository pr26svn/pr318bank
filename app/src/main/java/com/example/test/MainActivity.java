package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat format= new SimpleDateFormat("dd.MM.yyyy");
        TextView Date_txt=findViewById(R.id.textView9);
        Date_txt.setText((format.format(new Date())));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
        URL githubEndpoint = null;
        try {
            githubEndpoint = new URL("http://api.areas.su/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection myConnection= null;
        try {
            myConnection = (HttpsURLConnection)githubEndpoint.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

        myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
        myConnection.setRequestProperty("Contect-Me", "hathibelagal@example.com");

        try {
            if(myConnection.getResponseCode()==200)
            {

            }
            else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream responseBody= myConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader responseBodyReader= new InputStreamReader(responseBody, "UTF-8");

        JsonReader jsonReader= new JsonReader(responseBodyReader);


    }
}