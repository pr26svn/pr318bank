package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
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

    Button changeBankButton;
    Button valueCourseButton;
    Button signInButton;

    TextView usdText;
    TextView eurText;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL githubEndpoint = new URL("http://api.areas.su/");
                    HttpsURLConnection connection = (HttpsURLConnection)githubEndpoint.openConnection();
                    connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    connection.setRequestProperty("Contact-Me", "hathibelagal@example.com");
                    if (connection.getResponseCode() == 200) {
                        InputStream responseBody = connection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);

                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            if (key.equals("organization_url")) {
                                String value = jsonReader.nextString();
                                break;
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.close();
                    }
                    else {
                    }
                    connection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        changeBankButton = (Button)findViewById(R.id.button_1);
        changeBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, changeBank.class);
                startActivity(intent);
            }
        });
        valueCourseButton = (Button)findViewById(R.id.button_2);
        valueCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, valueCourse.class);
                startActivity(intent);
            }
        });
        signInButton = (Button)findViewById(R.id.button_3);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        usdText = (TextView) findViewById(R.id.text_usd);
        eurText = (TextView) findViewById(R.id.text_eur);
        dateText = (TextView) findViewById(R.id.text_date);

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateText.setText(dateFormat.format(new Date()));
    }
}