package com.example.clone;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.branches_and_atms);



    }

    /*
    AsyncTask.execute(new Runnable()  {
        @Override
        public void run(){

        }
    })
    */

}
