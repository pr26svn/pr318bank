package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Currency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Create URL
                URL githubEndpoint = null;
                try {
                    githubEndpoint = new URL("https://api.github.com/");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                // Create connection
                try {
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) githubEndpoint.openConnection();

                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                    myConnection.setRequestProperty("Accept",
                            "application/vnd.github.v3+json");
                    myConnection.setRequestProperty("Contact-Me",
                            "hathibelagal@example.com");


                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");

                        JsonReader jsonReader = new JsonReader(responseBodyReader);


                        jsonReader.beginObject(); // Start processing the JSON object
                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            if (key.equals("organization_url")) { // Check if desired key
                                // Fetch the value as a String
                                String value = jsonReader.nextString();

                                // Do something with the value
                                // ...

                                break; // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }
                        }

                        jsonReader.close();
                        myConnection.disconnect();



                    } else {
                        // Error handling code goes here
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                URL httpbinEndpoint = null;
                try {
                    httpbinEndpoint = new URL("https://httpbin.org/post");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpsURLConnection myConnection
                        = null;
                try {
                    myConnection = (HttpsURLConnection) httpbinEndpoint.openConnection();
                    myConnection.setRequestMethod("POST");

                    HttpResponseCache myCache = HttpResponseCache.install(
                            getCacheDir(), 100000L);

                    if (myCache.getHitCount() > 0) {
                        // The cache is working
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });

    }



}

