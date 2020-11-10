package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        TextView Date_txt = findViewById(R.id.textView9);
        Date_txt.setText((format.format(new Date())));

        /*
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
        URL githubEndpoint = null;
        try {
            githubEndpoint = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=09/11/2020 ");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection myConnection = null;
        try {
            myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

        myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
        myConnection.setRequestProperty("Contect-Me", "hathibelagal@example.com");

        try {
            if (myConnection.getResponseCode() == 200) {

            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream responseBody = myConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream responseBody = null;
        InputStreamReader responseBodyReader = null;
        try {
            responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JsonReader jsonReader = new JsonReader(responseBodyReader);
        try {
            jsonReader.beginObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!jsonReader.hasNext()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String key = null;
            try {
                key = jsonReader.nextName();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (key.equals("organization_url")) {
                try {
                    String value = jsonReader.nextString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                try {
                    jsonReader.skipValue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myConnection.disconnect();

        URL httpbinEndpoint = null;
        try {
            httpbinEndpoint = new URL("https://httpbin.org/post");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpsURLConnection myConnectiob = (HttpsURLConnection) httpbinEndpoint.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            myConnection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        String myData = "message=Hello";
        myConnection.setDoInput(true);
        try {
            myConnection.getOutputStream().write(myData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            HttpResponseCache myCache = HttpResponseCache.install(getCacheDir(), 100000L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpResponseCache myCache = null;
        if (myCache.getHitCount() > 0) {

        }*/

        Button button=(Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Bank.class);
                startActivity(intent);
            }
        });
        Button button1=(Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Valute.class);
                startActivity(intent);
            }
        });
        Button button2=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
            }
        });

    }


}